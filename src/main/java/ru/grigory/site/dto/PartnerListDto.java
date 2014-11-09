package ru.grigory.site.dto;

import java.io.Serializable;

/**
 * Created by grigory on 02.11.14.
 */
public class PartnerListDto implements Serializable {
    private PartnerDto[] partners;

    public PartnerDto[] getPartners() {
        return partners;
    }

    public void setPartners(PartnerDto[] partners) {
        this.partners = partners;
    }
}
