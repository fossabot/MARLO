/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/


package org.cgiar.ccafs.marlo.rest.services.deliverables;

import org.cgiar.ccafs.marlo.rest.services.deliverables.model.Author;
import org.cgiar.ccafs.marlo.rest.services.deliverables.model.MetadataModel;
import org.cgiar.ccafs.marlo.utils.DateTypeAdapter;
import org.cgiar.ccafs.marlo.utils.RestConnectionUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IFPRIEBraryClientAPI extends MetadataClientApi {

  private static final Logger LOG = LoggerFactory.getLogger(IFPRIEBraryClientAPI.class);
  private final String ID_URL = "id";
  private final String COLLECTION_RUL = "collection";

  private final String REST_URL =
    "https://server15738.contentdm.oclc.org/dmwebservices/index.php?q=dmGetItemInfo/{0}/{1}/json";
  private RestConnectionUtil xmlReaderConnectionUtil;
  private Map<String, String> coverterAtrributes;

  public IFPRIEBraryClientAPI() {
    xmlReaderConnectionUtil = new RestConnectionUtil();
    coverterAtrributes = new HashMap<String, String>();
    coverterAtrributes.put("dmcreated", "publicationDate");
    coverterAtrributes.put("langua", "language");
    coverterAtrributes.put("loc", "keywords");
    coverterAtrributes.put("full", "citation");
    coverterAtrributes.put("rights", "rights.desc");
  }

  @Override
  public MetadataModel getMetadata(String link) {
    MetadataModel metadataModel = new MetadataModel();
    JSONObject jo = new JSONObject();

    try {
      String metadata = xmlReaderConnectionUtil.getJsonRestClient(link);
      jo = new JSONObject(metadata);
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter());
      Gson gson = gsonBuilder.create();
      String data = jo.toString();
      List<Author> authors = new ArrayList<Author>();
      if (jo.has("orcid") && jo.get("orcid") != null) {
        try {
          String authorJson = jo.getString("orcid");
          String authorsJson[] = authorJson.split("; ");
          for (String string : authorsJson) {
            string = string.replace(", ", "{0}");
            String div[] = string.split(" ");
            if (div.length == 2) {
              String firstName = div[1].replace("{0}", ", ");
              Author author = new Author(firstName);
              String names[] = author.getFirstName().split(", ");
              if (names.length == 2) {
                author.setFirstName(names[1]);
                author.setLastName(names[0]);
              }
              author.setOrcidId(div[0]);
              authors.add(author);
            }

          }
        } catch (JSONException e) {
          LOG.error("No authors");
        }
      }

      for (String key : coverterAtrributes.keySet()) {
        data = data.replace(key, coverterAtrributes.get(key));
      }
      // Remove empty doi to avoid Json exception
      data = data.replace("\"doi\":{}", "\"doi\":\"\"");
      metadataModel = gson.fromJson(data, MetadataModel.class);
      Author[] authorsArr = new Author[authors.size()];
      authorsArr = authors.toArray(authorsArr);
      metadataModel.setAuthors(authorsArr);
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error(e.getLocalizedMessage());
      jo = null;
    }

    return metadataModel;
  }

  /**
   * with the link evaluate host extract the ido and format into the rest url
   * 
   * @return the link to get the metadata
   */
  @Override
  public String parseLink(String link) {
    String linkRest = "", id = "", collection = "";

    String[] linkSplits = link.split("/");
    int i = 0;
    for (String linkSplit : linkSplits) {
      if (linkSplit.contains(ID_URL)) {
        id = linkSplits[i + 1];
      }
      if (linkSplit.contains(COLLECTION_RUL)) {
        collection = linkSplits[i + 1];
      }
      i++;
    }

    if (id.isEmpty() || collection.isEmpty()) {
      LOG.error(
        "Missing id or collection for IFPRIEBrary link.There was an error collecting data from the url: " + link);
    } else {
      linkRest = (REST_URL.replace("{0}", collection));
      linkRest = (linkRest.replace("{1}", id));
      this.setId(id);
    }

    return linkRest;
  }

}