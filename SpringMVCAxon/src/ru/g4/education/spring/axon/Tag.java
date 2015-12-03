package ru.g4.education.spring.axon;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
  @Id
  private String tagName;
  
  private String tagValue;
  
  @Override
  public String toString() {
    return "Tag [tagName=" + tagName + ", tagValue=" + tagValue + "]";
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
    result = prime * result + ((tagValue == null) ? 0 : tagValue.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tag other = (Tag) obj;
    if (tagName == null) {
      if (other.tagName != null)
        return false;
    } else if (!tagName.equals(other.tagName))
      return false;
    if (tagValue == null) {
      if (other.tagValue != null)
        return false;
    } else if (!tagValue.equals(other.tagValue))
      return false;
    return true;
  }
  
  public String getTagName() {
    return tagName;
  }
  
  public void setTagName(String tagName) {
    this.tagName = tagName;
  }
  
  public String getTagValue() {
    return tagValue;
  }
  
  public void setTagValue(String tagValue) {
    this.tagValue = tagValue;
  }
  
  
}
