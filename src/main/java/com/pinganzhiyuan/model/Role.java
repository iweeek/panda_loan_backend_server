package com.pinganzhiyuan.model;

public class Role {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column role.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column role.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private String name;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column role.description
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private String description;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column role.resource_ids
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private String resourceIds;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column role.is_enable
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private Boolean isEnable;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column role.id
     * @return  the value of role.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column role.id
     * @param id  the value for role.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column role.name
     * @return  the value of role.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column role.name
     * @param name  the value for role.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column role.description
     * @return  the value of role.description
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column role.description
     * @param description  the value for role.description
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column role.resource_ids
     * @return  the value of role.resource_ids
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column role.resource_ids
     * @param resourceIds  the value for role.resource_ids
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column role.is_enable
     * @return  the value of role.is_enable
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column role.is_enable
     * @param isEnable  the value for role.is_enable
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
}