package com.pinganzhiyuan.model;

public class ProductType {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column product_type.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column product_type.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private String name;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column product_type.sequency
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private Byte sequency;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column product_type.id
     * @return  the value of product_type.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column product_type.id
     * @param id  the value for product_type.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column product_type.name
     * @return  the value of product_type.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column product_type.name
     * @param name  the value for product_type.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column product_type.sequency
     * @return  the value of product_type.sequency
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public Byte getSequency() {
        return sequency;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column product_type.sequency
     * @param sequency  the value for product_type.sequency
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setSequency(Byte sequency) {
        this.sequency = sequency;
    }
}