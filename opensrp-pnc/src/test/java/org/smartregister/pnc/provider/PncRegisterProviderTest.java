package org.smartregister.pnc.provider;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.robolectric.util.ReflectionHelpers;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.pnc.BuildConfig;
import org.smartregister.pnc.PncLibrary;
import org.smartregister.pnc.config.BasePncRegisterProviderMetadata;
import org.smartregister.pnc.config.PncConfiguration;
import org.smartregister.pnc.config.PncRegisterQueryProviderContract;
import org.smartregister.pnc.config.PncRegisterRowOptions;
import org.smartregister.pnc.holder.FooterViewHolder;
import org.smartregister.pnc.holder.PncRegisterViewHolder;
import org.smartregister.repository.Repository;
import org.smartregister.view.contract.SmartRegisterClient;
import org.smartregister.view.contract.SmartRegisterClients;
import org.smartregister.view.dialog.FilterOption;
import org.smartregister.view.dialog.ServiceModeOption;
import org.smartregister.view.dialog.SortOption;
import org.smartregister.view.viewholder.OnClickFormLauncher;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class PncRegisterProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private PncRegisterProvider pncRegisterProvider;

    @Mock
    private Context context;

    @Mock
    private View.OnClickListener onClickListener;

    @Mock
    private View.OnClickListener paginationClickListener;

    @Mock
    private View mockedView;

    @Mock
    private LayoutInflater inflator;

    @Mock
    private Resources resources;

    @Before
    public void setUp() throws Exception {

        BasePncRegisterProviderMetadata pncRegisterProviderMetadata = Mockito.spy(new BasePncRegisterProviderMetadata());
        Mockito.doReturn(mockedView).when(inflator).inflate(Mockito.anyInt(), Mockito.any(ViewGroup.class), Mockito.anyBoolean());
        Mockito.doReturn(inflator).when(context).getSystemService(Mockito.eq(Context.LAYOUT_INFLATER_SERVICE));
        PowerMockito.doReturn(resources).when(context).getResources();

        PncRegisterRowOptions<? extends PncRegisterViewHolder> pncRegisterRowOption = PowerMockito.mock(PncRegisterRowOptions.class);

        PncConfiguration pncConfiguration = new PncConfiguration.Builder(PncRegisterQueryProvider.class)
                .setPncRegisterProviderMetadata(BasePncRegisterProviderMetadata.class)
                .setPncRegisterRowOptions(pncRegisterRowOption.getClass())
                .build();

        PncLibrary.init(Mockito.mock(org.smartregister.Context.class), Mockito.mock(Repository.class), pncConfiguration, BuildConfig.VERSION_CODE, 1);

        pncRegisterProvider = new PncRegisterProvider(context, onClickListener, paginationClickListener);
        ReflectionHelpers.setField(pncRegisterProvider, "pncRegisterProviderMetadata", pncRegisterProviderMetadata);
    }

    @After
    public void tearDown() throws Exception {
        ReflectionHelpers.setStaticField(PncLibrary.class, "instance", null);
    }

    @Test
    public void verifyFillValue() {

        TextView textView = PowerMockito.mock(TextView.class);
        PowerMockito.doNothing().when(textView).setText(anyString());

        PncRegisterProvider.fillValue(textView, "value");

        Mockito.verify(textView, Mockito.times(1)).setText("value");

    }

    // TODO: Fix this test
    /*@Test
    public void populatePatientColumnShouldCallProviderMetadataForDataValues() {
        CoreLibrary.init(Mockito.mock(org.smartregister.Context.class), Mockito.mock(SyncConfiguration.class));
        //PowerMockito.mockStatic(Utils.class);
        CommonPersonObjectClient client = Mockito.mock(CommonPersonObjectClient.class);
        Mockito.doReturn("2016-07-24T03:00:00.000+03:00")
                .when(pncRegisterProviderMetadata)
                .getDob(Mockito.any(Map.class));
        //PowerMockito.when(Utils.getDuration("2016-07-24T03:00:00.000+03:00")).thenReturn("3y 4m");
        Resources resources = Mockito.mock(Resources.class);
        Mockito.doReturn(resources).when(context).getResources();
        Mockito.doReturn("CG").when(resources).getString(R.string.care_giver_initials);
        Mockito.doReturn("y").when(resources).getString(R.string.abbrv_years);
        Mockito.doReturn("Age: %s").when(resources).getString(R.string.patient_age_holder);
        PncRegisterViewHolder viewHolder = Mockito.mock(PncRegisterViewHolder.class);
        viewHolder.patientColumn = Mockito.mock(View.class);
        viewHolder.dueButton = Mockito.mock(Button.class);
        pncRegisterProvider.populatePatientColumn(client, viewHolder);
        Mockito.verify(pncRegisterProviderMetadata, Mockito.times(1))
                .getClientFirstName(Mockito.eq(client.getColumnmaps()));
        Mockito.verify(pncRegisterProviderMetadata, Mockito.times(1))
                .getClientMiddleName(Mockito.eq(client.getColumnmaps()));
        Mockito.verify(pncRegisterProviderMetadata, Mockito.times(1))
                .getClientLastName(Mockito.eq(client.getColumnmaps()));
        Mockito.verify(pncRegisterProviderMetadata, Mockito.times(1))
                .getDob(Mockito.eq(client.getColumnmaps()));
        Mockito.verify(pncRegisterProviderMetadata, Mockito.times(1))
                .getGA(Mockito.eq(client.getColumnmaps()));
        Mockito.verify(pncRegisterProviderMetadata, Mockito.times(1))
                .getPatientID(Mockito.eq(client.getColumnmaps()));
    }*/

    @Test
    public void createViewHolderShouldUseCustomViewHolderinRowOptions() {
        PncRegisterRowOptions rowOptions = Mockito.mock(PncRegisterRowOptions.class);
        ReflectionHelpers.setField(pncRegisterProvider, "pncRegisterRowOptions", rowOptions);
        Mockito.doReturn(true).when(rowOptions).isCustomViewHolder();

        pncRegisterProvider.createViewHolder(Mockito.mock(ViewGroup.class));

        Mockito.verify(rowOptions, Mockito.times(1)).createCustomViewHolder(Mockito.any(View.class));
    }

    @Test
    public void createViewHolderShouldUseCustomLayoutIdProvided() {
        int layoutId = 49834;

        PncRegisterRowOptions rowOptions = Mockito.mock(PncRegisterRowOptions.class);
        ReflectionHelpers.setField(pncRegisterProvider, "pncRegisterRowOptions", rowOptions);
        Mockito.doReturn(true).when(rowOptions).useCustomViewLayout();
        Mockito.doReturn(layoutId).when(rowOptions).getCustomViewLayoutId();

        pncRegisterProvider.createViewHolder(Mockito.mock(ViewGroup.class));

        Mockito.verify(rowOptions, Mockito.times(2)).getCustomViewLayoutId();
        Mockito.verify(inflator, Mockito.times(1)).inflate(Mockito.eq(layoutId), Mockito.any(ViewGroup.class), Mockito.anyBoolean());
    }

    @Test
    public void getViewShouldCallRowOptionsPopulateClientRowWhenDefaultCustomImplementationIsProvided() {
        PncRegisterRowOptions rowOptions = Mockito.mock(PncRegisterRowOptions.class);
        ReflectionHelpers.setField(pncRegisterProvider, "pncRegisterRowOptions", rowOptions);

        Mockito.doReturn(true).when(rowOptions).isDefaultPopulatePatientColumn();

        pncRegisterProvider.getView(Mockito.mock(Cursor.class)
                , Mockito.mock(CommonPersonObjectClient.class)
                , Mockito.mock(PncRegisterViewHolder.class));

        Mockito.verify(rowOptions, Mockito.times(1)).populateClientRow(
                Mockito.any(Cursor.class)
                , Mockito.any(CommonPersonObjectClient.class)
                , Mockito.any(SmartRegisterClient.class)
                , Mockito.any(PncRegisterViewHolder.class));
    }

    @Test
    public void getViewShouldCallPopulatePatientColumn() {

        PncRegisterViewHolder pncRegisterViewHolder = Mockito.mock(PncRegisterViewHolder.class);
        View view = PowerMockito.mock(View.class);
        Button button = PowerMockito.mock(Button.class);
        View.OnClickListener onClickListener = PowerMockito.mock(View.OnClickListener.class);

        PowerMockito.doReturn("y").when(resources).getString(anyInt());
        PowerMockito.doReturn("Age: %s").when(context).getString(anyInt());
        ReflectionHelpers.setField(pncRegisterViewHolder, "patientColumn", view);
        ReflectionHelpers.setField(pncRegisterViewHolder, "dueButton", button);
        ReflectionHelpers.setField(pncRegisterProvider, "onClickListener", onClickListener);
        //PowerMockito.doNothing().when(view).setOnClickListener(onClickListener);

        pncRegisterProvider.getView(Mockito.mock(Cursor.class)
                , Mockito.mock(CommonPersonObjectClient.class)
                , pncRegisterViewHolder);


    }

    @Test
    public void getFooterViewShouldVerifyClickListenerAndShouldVisiblePreviousAndNextPageView() {

        FooterViewHolder footerViewHolder = PowerMockito.mock(FooterViewHolder.class);
        TextView pageInfoView = PowerMockito.mock(TextView.class);
        Button nextPageView = PowerMockito.mock(Button.class);
        Button previousPageView = PowerMockito.mock(Button.class);
        View.OnClickListener paginationClickListener = PowerMockito.mock(View.OnClickListener.class);

        ReflectionHelpers.setField(footerViewHolder, "pageInfoView", pageInfoView);
        ReflectionHelpers.setField(footerViewHolder, "nextPageView", nextPageView);
        ReflectionHelpers.setField(footerViewHolder, "previousPageView", previousPageView);
        ReflectionHelpers.setField(pncRegisterProvider, "paginationClickListener", paginationClickListener);

        PowerMockito.doReturn("Page {0} of {1}").when(context).getString(anyInt());


        pncRegisterProvider.getFooterView(footerViewHolder, 1, 10, true, true);

        Mockito.verify(pageInfoView, Mockito.times(1)).setText("Page 1 of 10");
        Mockito.verify(nextPageView, Mockito.times(1)).setVisibility(View.VISIBLE);
        Mockito.verify(previousPageView, Mockito.times(1)).setVisibility(View.VISIBLE);
        Mockito.verify(nextPageView, Mockito.times(1)).setOnClickListener(paginationClickListener);
        Mockito.verify(previousPageView, Mockito.times(1)).setOnClickListener(paginationClickListener);
    }

    @Test
    public void getFooterShouldInvisiblePreviousAndNextPageView() {

        FooterViewHolder footerViewHolder = PowerMockito.mock(FooterViewHolder.class);
        TextView pageInfoView = PowerMockito.mock(TextView.class);
        Button nextPageView = PowerMockito.mock(Button.class);
        Button previousPageView = PowerMockito.mock(Button.class);
        View.OnClickListener paginationClickListener = PowerMockito.mock(View.OnClickListener.class);

        ReflectionHelpers.setField(footerViewHolder, "pageInfoView", pageInfoView);
        ReflectionHelpers.setField(footerViewHolder, "nextPageView", nextPageView);
        ReflectionHelpers.setField(footerViewHolder, "previousPageView", previousPageView);
        ReflectionHelpers.setField(pncRegisterProvider, "paginationClickListener", paginationClickListener);

        PowerMockito.doReturn("Page {0} of {1}").when(context).getString(anyInt());

        pncRegisterProvider.getFooterView(footerViewHolder, 1, 10, false, false);

        Mockito.verify(nextPageView, Mockito.times(1)).setVisibility(View.INVISIBLE);
        Mockito.verify(previousPageView, Mockito.times(1)).setVisibility(View.INVISIBLE);
    }

    @Test
    public void updateClientsShouldReturnNull() {
        SmartRegisterClients client = pncRegisterProvider.updateClients(
                PowerMockito.mock(FilterOption.class),
                PowerMockito.mock(ServiceModeOption.class),
                PowerMockito.mock(FilterOption.class),
                PowerMockito.mock(SortOption.class)
        );

        Assert.assertNull(client);
    }

    @Test
    public void newFormLauncherShouldReturnNull() {
        OnClickFormLauncher formLauncher = pncRegisterProvider.newFormLauncher("", "", "");
        Assert.assertNull(formLauncher);
    }

    @Test
    public void onServiceModeSelectedDoNothing() {
        pncRegisterProvider.onServiceModeSelected(PowerMockito.mock(ServiceModeOption.class));
    }

    @Test
    public void inflaterShouldReturnMockedInflater() {
        LayoutInflater layoutInflater = pncRegisterProvider.inflater();
        Assert.assertEquals(inflator, layoutInflater);
    }

    @Test
    public void createFooterHolderShouldReturnFooterViewHolder() throws Exception{

        ViewGroup parent = PowerMockito.mock(ViewGroup.class);
        View view = PowerMockito.mock(View.class);

        PowerMockito.doReturn(view).when(inflator).inflate(anyInt(), any(ViewGroup.class), anyBoolean());

        RecyclerView.ViewHolder footerViewHolder = pncRegisterProvider.createFooterHolder(parent);

        Assert.assertThat(footerViewHolder, instanceOf(RecyclerView.ViewHolder.class));
    }

    @Test
    public void isFooterViewHolderShouldReturnTrue() {
        FooterViewHolder footerViewHolder = PowerMockito.mock(FooterViewHolder.class);
        boolean result = pncRegisterProvider.isFooterViewHolder(footerViewHolder);
        Assert.assertTrue(result);
    }

    static class PncRegisterQueryProvider extends PncRegisterQueryProviderContract {

        @NonNull
        @Override
        public String getObjectIdsQuery(@Nullable String filters, @Nullable String mainCondition) {
            return null;
        }

        @NonNull
        @Override
        public String[] countExecuteQueries(@Nullable String filters, @Nullable String mainCondition) {
            return new String[0];
        }

        @NonNull
        @Override
        public String mainSelectWhereIDsIn() {
            return null;
        }
    }
}
