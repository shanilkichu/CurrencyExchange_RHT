
package com.kichu.currencyexchange_rht.view.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyData {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("documentation")
    @Expose
    private String documentation;
    @SerializedName("terms_of_use")
    @Expose
    private String termsOfUse;
    @SerializedName("time_last_update_unix")
    @Expose
    private Integer timeLastUpdateUnix;
    @SerializedName("time_last_update_utc")
    @Expose
    private String timeLastUpdateUtc;
    @SerializedName("time_next_update_unix")
    @Expose
    private Integer timeNextUpdateUnix;
    @SerializedName("time_next_update_utc")
    @Expose
    private String timeNextUpdateUtc;
    @SerializedName("time_eol_unix")
    @Expose
    private Integer timeEolUnix;
    @SerializedName("base_code")
    @Expose
    private String baseCode;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrencyData() {
    }

    /**
     * 
     * @param result
     * @param timeNextUpdateUtc
     * @param timeEolUnix
     * @param baseCode
     * @param termsOfUse
     * @param provider
     * @param documentation
     * @param rates
     * @param timeLastUpdateUnix
     * @param timeNextUpdateUnix
     * @param timeLastUpdateUtc
     */
    public CurrencyData(String result, String provider, String documentation, String termsOfUse, Integer timeLastUpdateUnix, String timeLastUpdateUtc, Integer timeNextUpdateUnix, String timeNextUpdateUtc, Integer timeEolUnix, String baseCode, Rates rates) {
        super();
        this.result = result;
        this.provider = provider;
        this.documentation = documentation;
        this.termsOfUse = termsOfUse;
        this.timeLastUpdateUnix = timeLastUpdateUnix;
        this.timeLastUpdateUtc = timeLastUpdateUtc;
        this.timeNextUpdateUnix = timeNextUpdateUnix;
        this.timeNextUpdateUtc = timeNextUpdateUtc;
        this.timeEolUnix = timeEolUnix;
        this.baseCode = baseCode;
        this.rates = rates;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public Integer getTimeLastUpdateUnix() {
        return timeLastUpdateUnix;
    }

    public void setTimeLastUpdateUnix(Integer timeLastUpdateUnix) {
        this.timeLastUpdateUnix = timeLastUpdateUnix;
    }

    public String getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    public void setTimeLastUpdateUtc(String timeLastUpdateUtc) {
        this.timeLastUpdateUtc = timeLastUpdateUtc;
    }

    public Integer getTimeNextUpdateUnix() {
        return timeNextUpdateUnix;
    }

    public void setTimeNextUpdateUnix(Integer timeNextUpdateUnix) {
        this.timeNextUpdateUnix = timeNextUpdateUnix;
    }

    public String getTimeNextUpdateUtc() {
        return timeNextUpdateUtc;
    }

    public void setTimeNextUpdateUtc(String timeNextUpdateUtc) {
        this.timeNextUpdateUtc = timeNextUpdateUtc;
    }

    public Integer getTimeEolUnix() {
        return timeEolUnix;
    }

    public void setTimeEolUnix(Integer timeEolUnix) {
        this.timeEolUnix = timeEolUnix;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

}
