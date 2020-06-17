package org.smartregister.pnc.utils;

/**
 * Created by Ephraim Kigamba - ekigamba@ona.io on 2019-11-29
 */

public interface PncDbConstants {

    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    interface KEY {
        String ID = "_id";
        String FIRST_NAME = "first_name";
        String MIDDLE_NAME = "middle_name";
        String LAST_NAME = "last_name";
        String DOB = "dob";

        String REGISTER_ID = "register_id";
        String BASE_ENTITY_ID = "base_entity_id";
        String GA = "ga";
        String CONCEPTION_DATE = "conception_date";

        String TABLE = "ec_client";
        String OPENSRP_ID = "opensrp_id";
        String LAST_INTERACTED_WITH = "last_interacted_with";
        String DATE_REMOVED = "date_removed";
    }

    interface Column {

        interface Client {
            String ID = "_id";
            String PHOTO = "photo";
            String FIRST_NAME = "first_name";
            String LAST_NAME = "last_name";
            String BASE_ENTITY_ID = "base_entity_id";
            String DOB = "dob";
            String OPENSRP_ID = "opensrp_id";
            String RELATIONALID = "relationalid";
            String NATIONAL_ID = "national_id";
            String GENDER = "gender";
        }

        interface PncDetails {
            String ID = "_id";
            String BASE_ENTITY_ID = "base_entity_id";
            String PENDING_OUTCOME = "pending_outcome";
            String PARA = "para";
            String GRAVIDA = "gravida";
            String RECORDED_AT = "recorded_at";
            String CONCEPTION_DATE = "conception_date";
            String HIV_STATUS = "hiv_status";
            String EVENT_DATE = "event_date";
            String CREATED_AT = "created_at";
        }

        interface PncBaby {
            String MOTHER_BASE_ENTITY_ID = "mother_base_entity_id";
            String DISCHARGED_ALIVE = "discharged_alive";
            String CHILD_REGISTERED = "child_registered";
            String BIRTH_RECORD = "birth_record_date";
            String FIRST_NAME = "first_name";
            String LAST_NAME = "last_name";
            String DOB = "dob";
            String GENDER = "gender";
            String BIRTH_WEIGTH_ENTERED = "birth_weight_entered";
            String BIRTH_WEIGHT = "birth_weight";
            String BIRTH_HEIGHT_ENTERED = "birth_height_entered";
            String APGAR = "apgar";
            String FIRST_CRY = "first_cry";
            String COMPLICATIONS = "complications";
            String COMPLICATIONS_OTHER = "complications_other";
            String CARE_MGT = "care_mgt";
            String CARE_MGT_SPECIFY = "care_mgt_specify";
            String REF_LOCATION = "referral_location";
            String BF_FIRST_HOUR = "bf_first_hour";
            String NVP_ADMINISTRATION = "nvp_administration";
            String CHILD_HIV_STATUS = "child_hiv_status";
        }

        interface PncStillBorn {
            String STILL_BIRTH_CONDITION = "still_birth_condition";
        }
    }

    interface Table {
        String EC_CLIENT = "ec_client";
        String PNC_DETAILS = "pnc_details";
        String PNC_REGISTRATION_DETAILS = "pnc_registration_details";
        String PNC_OUTCOME_FORM = "pnc_outcome_form";
        String PNC_BABY = "pnc_baby";
        String PNC_STILL_BORN = "pnc_still_born";
    }
}
