package Entities;

import java.util.Objects;

public class Service {
    private ServiceType serviceType;

    public Service(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Service service = (Service) o;

        return serviceType.equals(service.serviceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceType);
    }

    //Getters & Setters
    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
