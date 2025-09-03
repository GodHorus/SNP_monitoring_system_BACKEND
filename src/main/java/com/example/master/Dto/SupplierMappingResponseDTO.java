package com.example.master.Dto;


import java.util.List;

public class SupplierMappingResponseDTO {

    private Long id;
    private DemandDTO demand;
    private SupplierDTO supplier;
    private DistrictDTO district;
    private List<CdpoDTO> cdpos;
    private List<SectorDTO> sectors;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DemandDTO getDemand() {
        return demand;
    }

    public void setDemand(DemandDTO demand) {
        this.demand = demand;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public List<CdpoDTO> getCdpos() {
        return cdpos;
    }

    public void setCdpos(List<CdpoDTO> cdpos) {
        this.cdpos = cdpos;
    }

    public List<SectorDTO> getSectors() {
        return sectors;
    }

    public void setSectors(List<SectorDTO> sectors) {
        this.sectors = sectors;
    }

    // Inner DTOs for Demand, Supplier, and District
    public static class DemandDTO {
        private Long id;
        private String name; // Assuming you have a name in the Demand class

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SupplierDTO {
        private Long id;
        private String name; // Assuming Supplier has a name property

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DistrictDTO {
        private Long id;
        private String name; // Assuming District has a name property

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CdpoDTO {
        private Long id;
        private String cdpoName;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getCdpoName() {
            return cdpoName;
        }
        public void setCdpoName(String cdpoName) {
            this.cdpoName = cdpoName;
        }
    }

    public static class SectorDTO {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
