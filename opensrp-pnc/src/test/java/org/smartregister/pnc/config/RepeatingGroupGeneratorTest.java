package org.smartregister.pnc.config;

import com.vijay.jsonwizard.constants.JsonFormConstants;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.smartregister.pnc.BaseTest;
import org.smartregister.pnc.utils.PncDbConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatingGroupGeneratorTest extends BaseTest {

    private String sampleStep = "{\"title\":\"Live Births\",\"fields\":[{\"key\":\"baby_alive_group\",\"type\":\"repeating_group\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"164894AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"reference_edit_text_hint\":\"#Total baby born alive\",\"repeating_group_label\":\"Baby born alive\",\"value\":[{\"key\":\"discharged_alive\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1695AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"native_radio\",\"label\":\"Was the baby discharged alive?\",\"label_text_style\":\"bold\",\"v_required\":{\"value\":\"true\",\"err\":\"Field required\"},\"options\":[{\"key\":\"yes\",\"text\":\"Yes\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1692AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"no\",\"text\":\"No\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"159AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"birth_record_date\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"5599AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"date_picker\",\"hint\":\"Birth Record Date\",\"max_date\":\"today\",\"v_required\":{\"value\":\"true\",\"err\":\"Field required\"}},{\"key\":\"baby_first_name\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"first_name\",\"type\":\"edit_text\",\"hint\":\"First name\",\"v_required\":{\"value\":\"true\",\"err\":\"Field required\"}},{\"key\":\"baby_last_name\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"last_name\",\"type\":\"edit_text\",\"hint\":\"Last name\",\"v_required\":{\"value\":\"true\",\"err\":\"Field required\"}},{\"key\":\"baby_dob\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"birthdate\",\"type\":\"date_picker\",\"hint\":\"Birth date\",\"v_required\":{\"value\":\"true\",\"err\":\"Field required\"}},{\"key\":\"baby_gender\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"gender\",\"type\":\"native_radio\",\"label\":\"Child's gender\",\"label_text_style\":\"bold\",\"v_required\":{\"value\":\"true\",\"err\":\"Select at least one medical condition OR None\"},\"options\":[{\"key\":\"male\",\"text\":\"Male\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1535AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"female\",\"text\":\"Female\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1534AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"birth_weight_entered\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\",\"type\":\"edit_text\",\"hint\":\"Birth weight (gm)\",\"v_numeric\":{\"value\":\"true\",\"err\":\"Enter valid birth weight\"},\"v_required\":{\"value\":\"true\",\"err\":\"Enter valid birth weight\"}},{\"key\":\"birth_weight\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"5916AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"hidden\",\"calculation\":{\"rules-engine\":{\"ex-rules\":{\"rules-dynamic\":\"pnc/medic_calculation_rules.yml\"}}}},{\"key\":\"birth_height_entered\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"1503AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"edit_text\",\"hint\":\"Birth length (cm)\",\"v_numeric\":{\"value\":\"true\",\"err\":\"Enter valid birth height\"}},{\"key\":\"apgar\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"1504AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"edit_text\",\"hint\":\"APGAR score\",\"v_numeric\":{\"value\":\"true\",\"err\":\"Enter valid agpar\"},\"v_required\":{\"value\":\"true\",\"err\":\"Enter valid agpar\"},\"constraints\":[{\"type\":\"numeric\",\"ex\":\"greaterThanEqualTo(., \\\"0\\\")\",\"err\":\"agpar must be greater than or equal to 0\"},{\"type\":\"numeric\",\"ex\":\"lessThanEqualTo(., \\\"10\\\")\",\"err\":\"agpar must be less than or equal to 10\"}]},{\"key\":\"baby_first_cry\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\",\"type\":\"native_radio\",\"label\":\"Did the baby cry at birth?\",\"label_text_style\":\"bold\",\"v_required\":{\"value\":\"true\",\"err\":\"Select at least one medical condition OR None\"},\"options\":[{\"key\":\"yes\",\"text\":\"Yes\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\"},{\"key\":\"no\",\"text\":\"No\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\"}]},{\"key\":\"baby_complications\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"164122AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"check_box\",\"label\":\"Newborn care complications\",\"v_required\":{\"value\":\"true\",\"err\":\"Select at least one medical condition OR None\"},\"exclusive\":[\"none\"],\"options\":[{\"key\":\"none\",\"text\":\"None\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1107AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"premature\",\"text\":\"Premature\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"159908AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"asphyxia\",\"text\":\"Asphyxia\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"121397AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"sepsis\",\"text\":\"Sepsis\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"226AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"birth_defects\",\"text\":\"Birth defects\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"119975AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"other\",\"text\":\"Other (Specify)\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"5622AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"baby_complications_other\",\"openmrs_entity_parent\":\"164122AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"160632AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"edit_text\",\"hint\":\"Specify\",\"relevance\":{\"step4:baby_complications\":{\"ex-checkbox\":[{\"or\":[\"other\"]}]}}},{\"key\":\"baby_care_mgt\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"159839AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"check_box\",\"label\":\"Newborn Routine Care & Management\",\"relevance\":{\"step4:baby_complications\":{\"ex-checkbox\":[{\"or\":[\"none\"]}]}},\"options\":[{\"key\":\"kangaroo_mother_care\",\"text\":\"Kangaroo Mother Care (KMC)\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"164173AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"antibiotics\",\"text\":\"Antibiotics\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1195AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"tetracycline_eye_ointment\",\"text\":\"Tetracycline Eye Ointment (TEO)\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1520AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"chlorhexidine\",\"text\":\"Chlorhexidine (7.1%)\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"\"},{\"key\":\"vitamin_k\",\"text\":\"Vitamin K\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"86352AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"rescuscitation\",\"text\":\"Rescuscitation\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"162131AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"referral\",\"text\":\"Referral\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1648AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"other\",\"text\":\"Other (Specify)\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"5622AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"baby_care_mgt_specify\",\"openmrs_entity_parent\":\"164122AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"160632AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"edit_text\",\"hint\":\"Specify\",\"relevance\":{\"step4:baby_care_mgt\":{\"ex-checkbox\":[{\"or\":[\"other\"]}]}}},{\"key\":\"baby_referral_location\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"1272AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"native_radio\",\"label\":\"Specify the location of the referral\",\"label_text_style\":\"bold\",\"v_required\":{\"value\":\"true\",\"err\":\"Select at least one medical condition OR None\"},\"relevance\":{\"step4:baby_care_mgt\":{\"ex-checkbox\":[{\"or\":[\"referral\"]}]}},\"options\":[{\"key\":\"the_nursery\",\"text\":\"The nursery\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\"},{\"key\":\"other_facility\",\"text\":\"Other facility\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\"}]},{\"key\":\"bf_first_hour\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"161543AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"native_radio\",\"label\":\"Breastfeeding initiated within 60 mins\",\"label_text_style\":\"bold\",\"relevance\":{\"step4:discharged_alive\":{\"type\":\"string\",\"ex\":\"equalTo(., \\\"yes\\\")\"}},\"options\":[{\"key\":\"yes\",\"text\":\"Yes\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"1065AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"no\",\"text\":\"No\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"1066AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"child_hiv_status\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"5303AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"check_box\",\"label\":\"Child's HIV status\",\"v_required\":{\"value\":\"true\",\"err\":\"Select at least one medical condition OR None\"},\"options\":[{\"key\":\"positive\",\"text\":\"Positive\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"703AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"negative\",\"text\":\"Negative\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"664AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"unknown\",\"text\":\"Unknown\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1067AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"exposed\",\"text\":\"Exposed\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"822AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"nvp_administration\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"80586AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"native_radio\",\"label\":\"NVP Administration Started\",\"label_text_style\":\"bold\",\"relevance\":{\"step4:child_hiv_status\":{\"type\":\"string\",\"ex\":\"equalTo(., \\\"[\\\"positive\\\",\\\"exposed\\\"]\\\")\"}},\"v_required\":{\"value\":\"true\",\"err\":\"Select at least one medical condition OR None\"},\"options\":[{\"key\":\"yes\",\"text\":\"Yes\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"1065AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"no\",\"text\":\"No\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"1066AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]}]}]}";

    @Test
    public void testInitShouldGenerateRepeatingGrpFields() throws JSONException {
        JSONObject step = new JSONObject(sampleStep);
        String repeatingGrpkey = "baby_alive_group";
        String uniqueKey = PncDbConstants.Column.PncBaby.BASE_ENTITY_ID;
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("baby_first_name","first_name");
        columnMap.put("baby_last_name","last_name");
        columnMap.put("baby_dob","dob");
        List<HashMap<String, String>> storedValues = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put(uniqueKey, "23332-aeqw2");
        map.put("dob", "2000-09-09");
        map.put("first_name", "John");
        map.put("last_name", "Doe");
        map.put("birth_weight", "20");
        storedValues.add(map);

        new RepeatingGroupGenerator(step, "stepName", repeatingGrpkey,
                columnMap,
                uniqueKey,
                storedValues).init();

        Assert.assertTrue(step.optJSONArray(JsonFormConstants.FIELDS).length() > 1);
    }
}