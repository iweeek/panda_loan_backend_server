package com.pinganzhiyuan.model;

public class Term {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column term.id
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column term.duration
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    private String duration;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column term.sequency
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    private Integer sequency;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column term.id
     * @return  the value of term.id
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column term.id
     * @param id  the value for term.id
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column term.duration
     * @return  the value of term.duration
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    public String getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column term.duration
     * @param duration  the value for term.duration
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column term.sequency
     * @return  the value of term.sequency
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    public Integer getSequency() {
        return sequency;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column term.sequency
     * @param sequency  the value for term.sequency
     * @mbg.generated  Mon Jan 22 22:10:21 CST 2018
     */
    public void setSequency(Integer sequency) {
        this.sequency = sequency;
    }
}