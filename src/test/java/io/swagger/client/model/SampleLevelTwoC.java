/*
 * Sample Openapi Document 01
 * This is a sample yaml for yaml-doc-tool
 *
 * OpenAPI spec version: 1.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * SampleLevelTwoC
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-10-06T19:43:41.743004107Z[GMT]")

public class SampleLevelTwoC {
  @SerializedName("note")
  private String note = null;

  @SerializedName("description")
  private String description = null;

  public SampleLevelTwoC note(String note) {
    this.note = note;
    return this;
  }

   /**
   * Level two description
   * @return note
  **/
  @Schema(example = "Level two notes C", description = "Level two description")
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public SampleLevelTwoC description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Level two description
   * @return description
  **/
  @Schema(example = "Level two description C", description = "Level two description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SampleLevelTwoC sampleLevelTwoC = (SampleLevelTwoC) o;
    return Objects.equals(this.note, sampleLevelTwoC.note) &&
        Objects.equals(this.description, sampleLevelTwoC.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(note, description);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SampleLevelTwoC {\n");
    
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}