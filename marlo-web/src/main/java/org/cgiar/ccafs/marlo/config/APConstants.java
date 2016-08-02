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

package org.cgiar.ccafs.marlo.config;


/**
 * All Constants should be here.
 * 
 * @author Hermes Jiménez - CIAT/CCAFS
 * @author Christian Garcia.
 * @author Héctor Fabio Tobón R.
 */
public final class APConstants {

  public static final String CUSTOM_LAGUAGE = "en";
  public static final String PATH_CUSTOM_FILES = "custom/";
  public static final String CUSTOM_FILE = "global";

  // Session variables
  public static final String SESSION_USER = "current_user";
  public static final String SESSION_CRP = "current_crp";
  public static final String USER_TOKEN = "user_token";

  // Crp Parameters
  public static final String CRP_PARAMETERS = "crp_parameters";
  public static final String CRP_LANGUAGE = "crp_language";
  public static final String CRP_CUSTOM_FILE = "crp_custom_file";
  public static final String CRP_HAS_REGIONS = "crp_has_regions";
  public static final String CRP_PMU_ROLE = "crp_pmu_rol";
  public static final String CRP_CL_ROLE = "crp_cl_rol";
  public static final String CRP_FPL_ROLE = "crp_fpl_rol";
  public static final String CRP_PL_ROLE = "crp_pl_rol";
  public static final String CRP_PC_ROLE = "crp_pc_rol";
  public static final String CRP_RPL_ROLE = "crp_rpl_rol";
  public static final String CRP_SL_ROLE = "crp_sl_rol";
  public static final String CRP_PROGRAM_ID = "crpProgramID";
  public static final String CRP_ID = "crpID";
  public static final String TRANSACTION_ID = "transactionId";
  public static final String CRP_ADMIN_ACTIVE = "crp_admin_active";
  public static final String CRP_IMPACT_PATHWAY_ACTIVE = "crp_impPath_active";
  public static final String SECTION_NAME = "sectionName";
  public static final String IDO_ID = "idoID";
  // Query parameter
  public static final String QUERY_PARAMETER = "q";

  // Outlook institutional email
  public static final String OUTLOOK_EMAIL = "cgiar.org";

  // Types of project
  public static final String PROJECT_CORE = "CCAFS_CORE";
  public static final String PROJECT_CCAFS_COFUNDED = "CCAFS_COFUNDED";
  public static final String PROJECT_BILATERAL = "BILATERAL";

  // Request variables
  public static final String EDITABLE_REQUEST = "edit";
  public static final String CRP_REQUEST = "crp";
  public static final String PROJECT_REQUEST_ID = "projectID";
  public static final String AUTOSAVE_REQUEST = "autoSave";
  public static final String COUNTRY_REQUEST_ID = "countryID";
  public static final String INSTITUTION_TYPE_REQUEST_ID = "institutionTypeID";
  // Types of Project Partners
  public static final String PROJECT_PARTNER_PL = "PL";
  public static final String PROJECT_PARTNER_PC = "PC";
  public static final String PROJECT_PARTNER_CP = "CP";

  // login messages and status
  public static final String LOGIN_STATUS = "loginStatus";
  public static final String LOGIN_MESSAGE = "loginMessage";

  public static final String LOGON_SUCCES = "LOGON_SUCCES";
  public static final String ERROR_NO_SUCH_USER = "ERROR_NO_SUCH_USER";
  public static final String ERROR_LOGON_FAILURE = "ERROR_LOGON_FAILURE";
  public static final String ERROR_INVALID_LOGON_HOURS = "ERROR_INVALID_LOGON_HOURS";
  public static final String ERROR_PASSWORD_EXPIRED = "ERROR_PASSWORD_EXPIRED";
  public static final String ERROR_ACCOUNT_DISABLED = "ERROR_ACCOUNT_DISABLED";
  public static final String ERROR_ACCOUNT_EXPIRED = "ERROR_ACCOUNT_EXPIRED";
  public static final String ERROR_ACCOUNT_LOCKED_OUT = "ERROR_ACCOUNT_LOCKED_OUT";
  public static final String USER_DISABLED = "USER_DISABLED";

  // new Target unit
  public static final String TARGET_UNIT_NAME = "targetUnitName";
  public static final long TARGET_UNIT_OTHER_ID = 18;

  // Date Formats
  public static final String DATE_FORMAT = "yyyy-MM-dd";
}

