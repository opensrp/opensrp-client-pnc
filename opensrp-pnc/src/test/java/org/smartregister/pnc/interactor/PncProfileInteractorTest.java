package org.smartregister.pnc.interactor;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.robolectric.util.ReflectionHelpers;
import org.smartregister.Context;
import org.smartregister.clientandeventmodel.Client;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.pnc.BaseTest;
import org.smartregister.pnc.PncLibrary;
import org.smartregister.pnc.pojo.PncEventClient;
import org.smartregister.pnc.pojo.RegisterParams;
import org.smartregister.pnc.utils.AppExecutors;
import org.smartregister.pnc.utils.PncJsonFormUtils;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.sync.helper.ECSyncHelper;

public class PncProfileInteractorTest extends BaseTest {

    private PncProfileInteractor interactor;

    @Captor
    private ArgumentCaptor syncHelperAddEventArgumentCaptor;

    private String registrationForm = "{\"count\":\"2\",\"encounter_type\":\"PNC Registration\",\"entity_id\":\"\",\"relational_id\":\"\",\"metadata\":{\"start\":{\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_data_type\":\"start\",\"openmrs_entity_id\":\"163137AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},\"end\":{\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_data_type\":\"end\",\"openmrs_entity_id\":\"163138AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},\"today\":{\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"encounter\",\"openmrs_entity_id\":\"encounter_date\"},\"deviceid\":{\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_data_type\":\"deviceid\",\"openmrs_entity_id\":\"163149AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},\"subscriberid\":{\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_data_type\":\"subscriberid\",\"openmrs_entity_id\":\"163150AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},\"simserial\":{\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_data_type\":\"simserial\",\"openmrs_entity_id\":\"163151AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},\"phonenumber\":{\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_data_type\":\"phonenumber\",\"openmrs_entity_id\":\"163152AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},\"encounter_location\":\"\",\"look_up\":{\"entity_id\":\"\",\"value\":\"\"}},\"step1\":{\"title\":\"PNC Registration\",\"next\":\"step2\",\"fields\":[{\"key\":\"photo\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\",\"type\":\"choose_image\",\"uploadButtonText\":\"Take a picture of the woman\"},{\"key\":\"OPENSRP_ID\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person_identifier\",\"openmrs_entity_id\":\"zeir_id\",\"type\":\"barcode\",\"barcode_type\":\"qrcode\",\"hint\":\"MER ID\",\"value\":\"0\",\"scanButtonText\":\"Scan QR Code\",\"v_numeric\":{\"value\":\"true\",\"err\":\"Please enter a valid MER ID\"},\"v_required\":{\"value\":\"true\",\"err\":\"Please enter the MER ID\"}},{\"key\":\"national_id\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person_attribute\",\"openmrs_entity_id\":\"national_id\",\"type\":\"edit_text\",\"hint\":\"National ID\"},{\"key\":\"first_name\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"first_name\",\"type\":\"edit_text\",\"hint\":\"First name\",\"edit_type\":\"name\",\"look_up\":\"true\",\"entity_id\":\"\",\"v_regex\":{\"value\":\"[A-Za-z\\\\s\\\\.\\\\-]*\",\"err\":\"Please enter a valid name\"},\"v_required\":{\"value\":true,\"err\":\"Please enter a first name\"}},{\"key\":\"last_name\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"last_name\",\"type\":\"edit_text\",\"hint\":\"Last name\",\"edit_type\":\"name\",\"look_up\":\"true\",\"v_required\":{\"value\":true,\"err\":\"Please enter the last name\"},\"v_regex\":{\"value\":\"[A-Za-z\\\\s\\\\.\\\\-]*\",\"err\":\"Please enter a valid name\"}},{\"key\":\"dob_entered\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"birthdate\",\"type\":\"date_picker\",\"hint\":\"Date of birth (DOB)\",\"expanded\":false,\"duration\":{\"label\":\"Age\"},\"min_date\":\"today-50y\",\"max_date\":\"today-10y\",\"v_required\":{\"value\":\"true\",\"err\":\"Please enter the date of birth\"},\"relevance\":{\"step1:dob_unknown\":{\"type\":\"string\",\"ex\":\"equalTo(., \\\"false\\\")\"}}},{\"key\":\"dob_unknown\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"dob_unknown\",\"type\":\"check_box\",\"hint\":\"DOB unknown checkbox\",\"label\":\"\",\"options\":[{\"key\":\"isDobUnknown\",\"text\":\"DOB unknown?\",\"value\":\"false\"}]},{\"key\":\"age_entered\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person_attribute\",\"openmrs_entity_id\":\"age\",\"type\":\"edit_text\",\"hint\":\"Age\",\"v_numeric\":{\"value\":\"true\",\"err\":\"Enter a valid Age\"},\"v_min\":{\"value\":\"10\",\"err\":\"Age must be 10 years and above\"},\"v_max\":{\"value\":\"50\",\"err\":\"Age should not be greater than 50 years\"},\"relevance\":{\"step1:dob_unknown\":{\"type\":\"string\",\"ex\":\"equalTo(., \\\"true\\\")\"}}},{\"key\":\"age_calculated\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\",\"type\":\"hidden\",\"value\":\"\",\"calculation\":{\"rules-engine\":{\"ex-rules\":{\"rules-file\":\"pnc/registration_calculation_rules.yml\"}}}},{\"key\":\"age\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\",\"type\":\"hidden\",\"value\":\"\",\"calculation\":{\"rules-engine\":{\"ex-rules\":{\"rules-file\":\"pnc/registration_calculation_rules.yml\"}}}},{\"key\":\"dob_calculated\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\",\"type\":\"hidden\",\"value\":\"\",\"calculation\":{\"rules-engine\":{\"ex-rules\":{\"rules-file\":\"pnc/registration_calculation_rules.yml\"}}}},{\"key\":\"Sex\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person\",\"openmrs_entity_id\":\"gender\",\"type\":\"edit_text\",\"hint\":\"Gender\",\"read_only\":true,\"value\":\"Female\"},{\"key\":\"home_address\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person_address\",\"openmrs_entity_id\":\"address2\",\"type\":\"edit_text\",\"hint\":\"Home address\",\"v_required\":{\"value\":false,\"err\":\"Please enter the Home Address\"}},{\"key\":\"village\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1354AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"tree\",\"tree\":[],\"hint\":\"Village\",\"v_required\":{\"value\":true,\"err\":\"Please enter the Woman's Village\"}},{\"key\":\"phone_number\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person_attribute\",\"openmrs_entity_id\":\"phone_number\",\"type\":\"edit_text\",\"hint\":\"Mobile Phone number\",\"v_regex\":{\"value\":\"([0][0-9]{9})|\\\\s*\",\"err\":\"Number must begin with 0 and must be a total of 10 digits in length\"}},{\"key\":\"alt_name\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person_attribute\",\"openmrs_entity_id\":\"alt_contact_name\",\"type\":\"edit_text\",\"hint\":\"Alternate Contact Name\",\"edit_type\":\"name\",\"look_up\":\"true\",\"entity_id\":\"\",\"v_regex\":{\"value\":\"[A-Za-z\\\\s\\\\.\\\\-]*\",\"err\":\"Please enter a valid name\"}},{\"key\":\"alt_phone_number\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"person_attribute\",\"openmrs_entity_id\":\"alt_contact_phone_number\",\"type\":\"edit_text\",\"hint\":\"Phone number for alternate contact\",\"v_regex\":{\"value\":\"([0][0-9]{9})|\\\\s*\",\"err\":\"Number must begin with 0 and must be a total of 10 digits in length\"}}]},\"step2\":{\"title\":\"DEMOGRAPHIC INFORMATION\",\"fields\":[{\"key\":\"educ_level\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1712AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"native_radio\",\"label\":\"Highest level of school\",\"label_text_style\":\"bold\",\"options\":[{\"key\":\"none\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1107AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"text\":\"None\"},{\"key\":\"dont_know\",\"text\":\"Don't know\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1067AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"primary\",\"text\":\"Primary\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1713AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"secondary\",\"text\":\"Secondary\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1714AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"higher\",\"text\":\"Higher\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"160292AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"marital_status\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1054AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"native_radio\",\"label\":\"Marital status\",\"label_text_style\":\"bold\",\"options\":[{\"key\":\"married\",\"text\":\"Married\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"5555AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"living_together\",\"text\":\"Living together\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1055AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"divorced\",\"text\":\"Divorced / separated\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1058AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"Married_and_not_living_together\",\"text\":\"Married & Not Living Together\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"163007AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"single\",\"text\":\"Never married and never lived together (single)\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"5615AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"widowed\",\"text\":\"Widowed\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1059AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"occupation\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1542AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"openmrs_data_type\":\"select one\",\"type\":\"check_box\",\"label\":\"Occupation\",\"hint\":\"Occupation\",\"label_text_style\":\"bold\",\"combine_checkbox_option_values\":\"true\",\"options\":[{\"key\":\"student\",\"text\":\"Student\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"159465AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"unemployed\",\"text\":\"Unemployed\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"123801AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"formal_employment\",\"text\":\"Formal employment\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"165219AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"informal_employment\",\"text\":\"Informal employment\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"159613AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"oc_business\",\"text\":\"Business\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\"},{\"key\":\"other\",\"text\":\"Other (specify)\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"5622AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"occupation_other\",\"openmrs_entity_parent\":\"1542AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"160632AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"edit_text\",\"hint\":\"Specify\",\"edit_type\":\"name\",\"relevance\":{\"rules-engine\":{\"ex-rules\":{\"rules-file\":\"pnc/registration_relevance_rules.yml\"}}},\"v_required\":{\"value\":false,\"err\":\"Please specify your occupation\"},\"v_regex\":{\"value\":\"[A-Za-z\\\\s\\\\.\\\\-]*\",\"err\":\"Please specify your occupation\"}},{\"key\":\"religion\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"162929AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"openmrs_data_type\":\"\",\"type\":\"native_radio\",\"label\":\"Religion\",\"hint\":\"Religion\",\"label_text_style\":\"bold\",\"options\":[{\"key\":\"christian\",\"text\":\"Christian\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"163125AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"muslim\",\"text\":\"Muslim\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"162933AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"traditional\",\"text\":\"Traditional\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\"},{\"key\":\"pagan\",\"text\":\"Pagan\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"\",\"openmrs_entity_id\":\"\"},{\"key\":\"none\",\"text\":\"None\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"1107AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"},{\"key\":\"other\",\"text\":\"Other (specify)\",\"openmrs_entity_parent\":\"\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"5622AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}]},{\"key\":\"religion_other\",\"openmrs_entity_parent\":\"162929AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"openmrs_entity\":\"concept\",\"openmrs_entity_id\":\"160632AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"type\":\"edit_text\",\"hint\":\"Write in the woman's other religion that is not on the list.\",\"edit_type\":\"name\",\"relevance\":{\"step2:religion\":{\"type\":\"string\",\"ex\":\"equalTo(., \\\"other\\\")\"}}}]}}";

    @Mock
    private PncLibrary pncLibrary;

    @Mock
    private Context opensrpContext;

    @Mock
    private AllSharedPreferences allSharedPreferences;


    @Before
    public void setUp() {
        Mockito.doReturn(new AppExecutors()).when(pncLibrary).getAppExecutors();
        ReflectionHelpers.setStaticField(PncLibrary.class, "instance", pncLibrary);
        interactor = new PncProfileInteractor();
    }

    @Test
    public void testSaveRegistrationShouldPassCorrectArguments() throws Exception {
        PncProfileInteractor pncProfileInteractor = Mockito.spy(interactor);
        String baseEntityId = "234-24";
        Client client = new Client(baseEntityId);
        client.addIdentifier(PncJsonFormUtils.OPENSRP_ID, "7899");
        Event event = new Event();
        event.setBaseEntityId(baseEntityId);
        event.setFormSubmissionId("3422-90");
        PncEventClient pncEventClient = new PncEventClient(client, event);
        RegisterParams params = new RegisterParams();
        params.setEditMode(false);

        Mockito.when(allSharedPreferences.fetchLastUpdatedAtDate(0)).thenReturn(1589270584000l);

        Mockito.doReturn(allSharedPreferences).when(opensrpContext).allSharedPreferences();
        Mockito.doReturn(opensrpContext).when(pncLibrary).context();

        ECSyncHelper ecSyncHelper = Mockito.mock(ECSyncHelper.class);
        Mockito.doReturn(ecSyncHelper).when(pncProfileInteractor).getSyncHelper();

        Whitebox.invokeMethod(pncProfileInteractor, "saveRegistration", pncEventClient, registrationForm, params);

        Mockito.verify(ecSyncHelper).addEvent((String) syncHelperAddEventArgumentCaptor.capture(), (JSONObject) syncHelperAddEventArgumentCaptor.capture(), (String) syncHelperAddEventArgumentCaptor.capture());

        Assert.assertNotNull(syncHelperAddEventArgumentCaptor.getAllValues().get(0));
        Assert.assertEquals(event.getBaseEntityId(), syncHelperAddEventArgumentCaptor.getAllValues().get(0));
        Assert.assertNotNull(syncHelperAddEventArgumentCaptor.getAllValues().get(1));
        JSONObject resultEventJson = (JSONObject) syncHelperAddEventArgumentCaptor.getAllValues().get(1);
        Assert.assertEquals(event.getFormSubmissionId(), resultEventJson.optString("formSubmissionId"));
    }

    @After
    public void tearDown() {
        ReflectionHelpers.setStaticField(PncLibrary.class, "instance", null);
    }
}