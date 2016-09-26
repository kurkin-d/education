package net.dontdrinkandroot.example.angularrestspringsecurity.rest.resources;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.newsentry.NewsEntryDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Component
//@Path("/news")
@RequestMapping("/rest/news")
public class NewsEntryResource
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NewsEntryDao newsEntryDao;

	//@Autowired
	//private ObjectMapper mapper;


	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<NewsEntry> list() throws /*JsonGenerationException, JsonMappingException,*/ IOException
	{
		this.logger.info("list()");

		/*ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		}*/
		List<NewsEntry> allEntries = this.newsEntryDao.findAll();
		return allEntries;
		//return viewWriter.writeValueAsString(allEntries);
	}


	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	//@Path("{id}")
	@RequestMapping(value = "{id}",method = RequestMethod.GET)
	@ResponseBody
	public NewsEntry read(/*@PathParam("id")*/@PathVariable("id") Long id) throws Exception {
		this.logger.info("read(id)");

		NewsEntry newsEntry = this.newsEntryDao.find(id);
		if (newsEntry == null) {
			throw new Exception("ошибка "+404);
		}
		return newsEntry;
	}


	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public NewsEntry create(@RequestBody NewsEntry newsEntry)
	{
		this.logger.info("create(): " + newsEntry);

		return this.newsEntryDao.save(newsEntry);
	}


	@RequestMapping(value = "{id}",method = RequestMethod.POST)
	@ResponseBody
	public NewsEntry update(/*@PathParam("id")*/ @PathVariable("id") Long id, @RequestBody NewsEntry newsEntry)
	{
		this.logger.info("update(): " + newsEntry);

		return this.newsEntryDao.save(newsEntry);
	}


	//@DELETE
	//@Produces(MediaType.APPLICATION_JSON)
	//@Path("{id}")
	@RequestMapping(value = "{id}",method = RequestMethod.DELETE)
	public void delete(/*@PathParam("id")*/ @PathVariable("id") Long id)
	{
		this.logger.info("delete(id)");

		this.newsEntryDao.delete(id);
	}


	private boolean isAdmin()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			return false;
		}
		UserDetails userDetails = (UserDetails) principal;

		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			if (authority.toString().equals("admin")) {
				return true;
			}
		}

		return false;
	}

}