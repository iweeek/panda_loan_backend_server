package com.pinganzhiyuan.model;

public class CreditAuth {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column credit_auth.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column credit_auth.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private String name;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column credit_auth.weight
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    private Integer weight;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column credit_auth.id
     * @return  the value of credit_auth.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column credit_auth.id
     * @param id  the value for credit_auth.id
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column credit_auth.name
     * @return  the value of credit_auth.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column credit_auth.name
     * @param name  the value for credit_auth.name
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column credit_auth.weight
     * @return  the value of credit_auth.weight
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column credit_auth.weight
     * @param weight  the value for credit_auth.weight
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}