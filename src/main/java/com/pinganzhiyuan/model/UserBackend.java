package com.pinganzhiyuan.model;

public class UserBackend {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column user_backend.id
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column user_backend.username
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private String username;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column user_backend.password
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private String password;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column user_backend.role_ids
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private String roleIds;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column user_backend.id
     * @return  the value of user_backend.id
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column user_backend.id
     * @param id  the value for user_backend.id
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column user_backend.username
     * @return  the value of user_backend.username
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column user_backend.username
     * @param username  the value for user_backend.username
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column user_backend.password
     * @return  the value of user_backend.password
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column user_backend.password
     * @param password  the value for user_backend.password
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column user_backend.role_ids
     * @return  the value of user_backend.role_ids
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public String getRoleIds() {
        return roleIds;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column user_backend.role_ids
     * @param roleIds  the value for user_backend.role_ids
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}