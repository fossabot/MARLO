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

package org.cgiar.ccafs.marlo.action;

import org.cgiar.ccafs.marlo.utils.APConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class SolrQueryAction extends BaseAction {


  private static final long serialVersionUID = -7574876891140953871L;


  Map<String, Object> firstMap;

  @Inject
  public SolrQueryAction(APConfig config) {
    super(config);
  }

  @Override
  public String execute() throws Exception {
    return SUCCESS;
  }

  public Map<String, Object> getFirstMap() {
    return firstMap;
  }

  @Override
  public void prepare() throws Exception {
    String sUrl =
      "http://ec2-52-211-37-10.eu-west-1.compute.amazonaws.com:8080/solr/ccafs-oss/select?q=*%3A*&wt=json&indent=true";
    URL url = new URL(sUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      System.out.println("Failed : HTTP error code : " + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

    StringBuilder jsonNew = new StringBuilder();
    String output;
    while ((output = br.readLine()) != null) {
      jsonNew.append(output);
    }
    conn.disconnect();
    Gson g = new Gson();
    Type mapType = new TypeToken<Map<String, Object>>() {
    }.getType();

    System.out.println(jsonNew.toString());
    this.firstMap = g.fromJson(jsonNew.toString(), mapType);
  }

  public void setFirstMap(Map<String, Object> firstMap) {
    this.firstMap = firstMap;
  }


}
