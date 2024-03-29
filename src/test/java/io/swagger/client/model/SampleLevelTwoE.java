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
 * SampleLevelTwoE
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-10-06T19:43:41.743004107Z[GMT]")

public class SampleLevelTwoE {
  @SerializedName("note")
  private String note = null;

  public SampleLevelTwoE note(String note) {
    this.note = note;
    return this;
  }

   /**
   * Level two description
   * @return note
  **/
  @Schema(example = "Level two notes E", description = "Level two description")
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SampleLevelTwoE sampleLevelTwoE = (SampleLevelTwoE) o;
    return Objects.equals(this.note, sampleLevelTwoE.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(note);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SampleLevelTwoE {\n");
    
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
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
