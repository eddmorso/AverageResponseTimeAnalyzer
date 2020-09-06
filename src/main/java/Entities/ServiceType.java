package Entities;

import Interfaces.ServiceConstants;

import java.util.Objects;

public class ServiceType extends Type {
    private int type;
    private int variation;
    private boolean anyVariation;

    public ServiceType(int type, int variation) {
        checkServiceType(type);
        checkServiceVariation(variation);

        this.type = type;
        this.variation = variation;
    }

    public ServiceType(int type) {
        checkServiceType(type);

        this.type = type;

        setAnyVariation(true);
    }

    public ServiceType() {
        setAnyType(true);
    }

    private void checkServiceType(int type) {
        if (!(type <= ServiceConstants.AMOUNT_OF_SERVICE_TYPES && type > 0))
            throw new RuntimeException("Wrong syntax");
    }

    private void checkServiceVariation(int variation) {
        if (!(variation <= ServiceConstants.AMOUNT_OF_SERVICE_VARIATIONS && variation > 0))
            throw new RuntimeException("Wrong syntax");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceType serviceType = (ServiceType) o;

        if (serviceType.isAnyType() || isAnyType()) {
            return true;
        }
        if (serviceType.isAnyVariation() || isAnyVariation()) {
            return type == serviceType.type;
        }
        return type == serviceType.type &&
                variation == serviceType.variation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, variation);
    }

    //Getters & Setters
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        setAnyType(false);
    }

    public int getVariation() {
        return variation;
    }

    public void setVariation(int variation) {
        this.variation = variation;
        setAnyType(false);
    }

    public boolean isAnyVariation() {
        return anyVariation;
    }

    public void setAnyVariation(boolean anyVariation) {
        this.anyVariation = anyVariation;
    }
}
