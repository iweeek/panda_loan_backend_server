package com.pinganzhiyuan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClientExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    protected String orderByClause;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    protected boolean distinct;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public ClientExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIdentityNoIsNull() {
            addCriterion("identity_no is null");
            return (Criteria) this;
        }

        public Criteria andIdentityNoIsNotNull() {
            addCriterion("identity_no is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityNoEqualTo(String value) {
            addCriterion("identity_no =", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotEqualTo(String value) {
            addCriterion("identity_no <>", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoGreaterThan(String value) {
            addCriterion("identity_no >", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoGreaterThanOrEqualTo(String value) {
            addCriterion("identity_no >=", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoLessThan(String value) {
            addCriterion("identity_no <", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoLessThanOrEqualTo(String value) {
            addCriterion("identity_no <=", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoLike(String value) {
            addCriterion("identity_no like", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotLike(String value) {
            addCriterion("identity_no not like", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoIn(List<String> values) {
            addCriterion("identity_no in", values, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotIn(List<String> values) {
            addCriterion("identity_no not in", values, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoBetween(String value1, String value2) {
            addCriterion("identity_no between", value1, value2, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotBetween(String value1, String value2) {
            addCriterion("identity_no not between", value1, value2, "identityNo");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrIsNull() {
            addCriterion("residence_addr is null");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrIsNotNull() {
            addCriterion("residence_addr is not null");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrEqualTo(String value) {
            addCriterion("residence_addr =", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrNotEqualTo(String value) {
            addCriterion("residence_addr <>", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrGreaterThan(String value) {
            addCriterion("residence_addr >", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrGreaterThanOrEqualTo(String value) {
            addCriterion("residence_addr >=", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrLessThan(String value) {
            addCriterion("residence_addr <", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrLessThanOrEqualTo(String value) {
            addCriterion("residence_addr <=", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrLike(String value) {
            addCriterion("residence_addr like", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrNotLike(String value) {
            addCriterion("residence_addr not like", value, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrIn(List<String> values) {
            addCriterion("residence_addr in", values, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrNotIn(List<String> values) {
            addCriterion("residence_addr not in", values, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrBetween(String value1, String value2) {
            addCriterion("residence_addr between", value1, value2, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andResidenceAddrNotBetween(String value1, String value2) {
            addCriterion("residence_addr not between", value1, value2, "residenceAddr");
            return (Criteria) this;
        }

        public Criteria andIsManIsNull() {
            addCriterion("is_man is null");
            return (Criteria) this;
        }

        public Criteria andIsManIsNotNull() {
            addCriterion("is_man is not null");
            return (Criteria) this;
        }

        public Criteria andIsManEqualTo(Boolean value) {
            addCriterion("is_man =", value, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManNotEqualTo(Boolean value) {
            addCriterion("is_man <>", value, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManGreaterThan(Boolean value) {
            addCriterion("is_man >", value, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_man >=", value, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManLessThan(Boolean value) {
            addCriterion("is_man <", value, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManLessThanOrEqualTo(Boolean value) {
            addCriterion("is_man <=", value, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManIn(List<Boolean> values) {
            addCriterion("is_man in", values, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManNotIn(List<Boolean> values) {
            addCriterion("is_man not in", values, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManBetween(Boolean value1, Boolean value2) {
            addCriterion("is_man between", value1, value2, "isMan");
            return (Criteria) this;
        }

        public Criteria andIsManNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_man not between", value1, value2, "isMan");
            return (Criteria) this;
        }

        public Criteria andNationIsNull() {
            addCriterion("nation is null");
            return (Criteria) this;
        }

        public Criteria andNationIsNotNull() {
            addCriterion("nation is not null");
            return (Criteria) this;
        }

        public Criteria andNationEqualTo(String value) {
            addCriterion("nation =", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotEqualTo(String value) {
            addCriterion("nation <>", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThan(String value) {
            addCriterion("nation >", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThanOrEqualTo(String value) {
            addCriterion("nation >=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThan(String value) {
            addCriterion("nation <", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThanOrEqualTo(String value) {
            addCriterion("nation <=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLike(String value) {
            addCriterion("nation like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotLike(String value) {
            addCriterion("nation not like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationIn(List<String> values) {
            addCriterion("nation in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotIn(List<String> values) {
            addCriterion("nation not in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationBetween(String value1, String value2) {
            addCriterion("nation between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotBetween(String value1, String value2) {
            addCriterion("nation not between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthIsNull() {
            addCriterion("identity_auth is null");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthIsNotNull() {
            addCriterion("identity_auth is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthEqualTo(String value) {
            addCriterion("identity_auth =", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthNotEqualTo(String value) {
            addCriterion("identity_auth <>", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthGreaterThan(String value) {
            addCriterion("identity_auth >", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthGreaterThanOrEqualTo(String value) {
            addCriterion("identity_auth >=", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthLessThan(String value) {
            addCriterion("identity_auth <", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthLessThanOrEqualTo(String value) {
            addCriterion("identity_auth <=", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthLike(String value) {
            addCriterion("identity_auth like", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthNotLike(String value) {
            addCriterion("identity_auth not like", value, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthIn(List<String> values) {
            addCriterion("identity_auth in", values, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthNotIn(List<String> values) {
            addCriterion("identity_auth not in", values, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthBetween(String value1, String value2) {
            addCriterion("identity_auth between", value1, value2, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityAuthNotBetween(String value1, String value2) {
            addCriterion("identity_auth not between", value1, value2, "identityAuth");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationIsNull() {
            addCriterion("identity_expiration is null");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationIsNotNull() {
            addCriterion("identity_expiration is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationEqualTo(Date value) {
            addCriterionForJDBCDate("identity_expiration =", value, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationNotEqualTo(Date value) {
            addCriterionForJDBCDate("identity_expiration <>", value, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationGreaterThan(Date value) {
            addCriterionForJDBCDate("identity_expiration >", value, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("identity_expiration >=", value, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationLessThan(Date value) {
            addCriterionForJDBCDate("identity_expiration <", value, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("identity_expiration <=", value, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationIn(List<Date> values) {
            addCriterionForJDBCDate("identity_expiration in", values, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationNotIn(List<Date> values) {
            addCriterionForJDBCDate("identity_expiration not in", values, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("identity_expiration between", value1, value2, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andIdentityExpirationNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("identity_expiration not between", value1, value2, "identityExpiration");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andGuaranteeIsNull() {
            addCriterion("guarantee is null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeIsNotNull() {
            addCriterion("guarantee is not null");
            return (Criteria) this;
        }

        public Criteria andGuaranteeEqualTo(String value) {
            addCriterion("guarantee =", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeNotEqualTo(String value) {
            addCriterion("guarantee <>", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGreaterThan(String value) {
            addCriterion("guarantee >", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeGreaterThanOrEqualTo(String value) {
            addCriterion("guarantee >=", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeLessThan(String value) {
            addCriterion("guarantee <", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeLessThanOrEqualTo(String value) {
            addCriterion("guarantee <=", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeLike(String value) {
            addCriterion("guarantee like", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeNotLike(String value) {
            addCriterion("guarantee not like", value, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeIn(List<String> values) {
            addCriterion("guarantee in", values, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeNotIn(List<String> values) {
            addCriterion("guarantee not in", values, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeBetween(String value1, String value2) {
            addCriterion("guarantee between", value1, value2, "guarantee");
            return (Criteria) this;
        }

        public Criteria andGuaranteeNotBetween(String value1, String value2) {
            addCriterion("guarantee not between", value1, value2, "guarantee");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNull() {
            addCriterion("profession is null");
            return (Criteria) this;
        }

        public Criteria andProfessionIsNotNull() {
            addCriterion("profession is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionEqualTo(String value) {
            addCriterion("profession =", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotEqualTo(String value) {
            addCriterion("profession <>", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThan(String value) {
            addCriterion("profession >", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionGreaterThanOrEqualTo(String value) {
            addCriterion("profession >=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThan(String value) {
            addCriterion("profession <", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLessThanOrEqualTo(String value) {
            addCriterion("profession <=", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionLike(String value) {
            addCriterion("profession like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotLike(String value) {
            addCriterion("profession not like", value, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionIn(List<String> values) {
            addCriterion("profession in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotIn(List<String> values) {
            addCriterion("profession not in", values, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionBetween(String value1, String value2) {
            addCriterion("profession between", value1, value2, "profession");
            return (Criteria) this;
        }

        public Criteria andProfessionNotBetween(String value1, String value2) {
            addCriterion("profession not between", value1, value2, "profession");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table client
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table client
     *
     * @mbg.generated do_not_delete_during_merge Fri Dec 22 17:57:42 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}