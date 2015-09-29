package com.doadway.glodmine.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpeedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpeedExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdateIsNull() {
            addCriterion("idate is null");
            return (Criteria) this;
        }

        public Criteria andIdateIsNotNull() {
            addCriterion("idate is not null");
            return (Criteria) this;
        }

        public Criteria andIdateEqualTo(Date value) {
            addCriterion("idate =", value, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateNotEqualTo(Date value) {
            addCriterion("idate <>", value, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateGreaterThan(Date value) {
            addCriterion("idate >", value, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateGreaterThanOrEqualTo(Date value) {
            addCriterion("idate >=", value, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateLessThan(Date value) {
            addCriterion("idate <", value, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateLessThanOrEqualTo(Date value) {
            addCriterion("idate <=", value, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateIn(List<Date> values) {
            addCriterion("idate in", values, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateNotIn(List<Date> values) {
            addCriterion("idate not in", values, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateBetween(Date value1, Date value2) {
            addCriterion("idate between", value1, value2, "idate");
            return (Criteria) this;
        }

        public Criteria andIdateNotBetween(Date value1, Date value2) {
            addCriterion("idate not between", value1, value2, "idate");
            return (Criteria) this;
        }

        public Criteria andIsourceIsNull() {
            addCriterion("isource is null");
            return (Criteria) this;
        }

        public Criteria andIsourceIsNotNull() {
            addCriterion("isource is not null");
            return (Criteria) this;
        }

        public Criteria andIsourceEqualTo(Integer value) {
            addCriterion("isource =", value, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceNotEqualTo(Integer value) {
            addCriterion("isource <>", value, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceGreaterThan(Integer value) {
            addCriterion("isource >", value, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("isource >=", value, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceLessThan(Integer value) {
            addCriterion("isource <", value, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceLessThanOrEqualTo(Integer value) {
            addCriterion("isource <=", value, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceIn(List<Integer> values) {
            addCriterion("isource in", values, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceNotIn(List<Integer> values) {
            addCriterion("isource not in", values, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceBetween(Integer value1, Integer value2) {
            addCriterion("isource between", value1, value2, "isource");
            return (Criteria) this;
        }

        public Criteria andIsourceNotBetween(Integer value1, Integer value2) {
            addCriterion("isource not between", value1, value2, "isource");
            return (Criteria) this;
        }

        public Criteria andImoneyIsNull() {
            addCriterion("imoney is null");
            return (Criteria) this;
        }

        public Criteria andImoneyIsNotNull() {
            addCriterion("imoney is not null");
            return (Criteria) this;
        }

        public Criteria andImoneyEqualTo(Float value) {
            addCriterion("imoney =", value, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyNotEqualTo(Float value) {
            addCriterion("imoney <>", value, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyGreaterThan(Float value) {
            addCriterion("imoney >", value, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyGreaterThanOrEqualTo(Float value) {
            addCriterion("imoney >=", value, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyLessThan(Float value) {
            addCriterion("imoney <", value, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyLessThanOrEqualTo(Float value) {
            addCriterion("imoney <=", value, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyIn(List<Float> values) {
            addCriterion("imoney in", values, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyNotIn(List<Float> values) {
            addCriterion("imoney not in", values, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyBetween(Float value1, Float value2) {
            addCriterion("imoney between", value1, value2, "imoney");
            return (Criteria) this;
        }

        public Criteria andImoneyNotBetween(Float value1, Float value2) {
            addCriterion("imoney not between", value1, value2, "imoney");
            return (Criteria) this;
        }

        public Criteria andImomoIsNull() {
            addCriterion("imomo is null");
            return (Criteria) this;
        }

        public Criteria andImomoIsNotNull() {
            addCriterion("imomo is not null");
            return (Criteria) this;
        }

        public Criteria andImomoEqualTo(String value) {
            addCriterion("imomo =", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoNotEqualTo(String value) {
            addCriterion("imomo <>", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoGreaterThan(String value) {
            addCriterion("imomo >", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoGreaterThanOrEqualTo(String value) {
            addCriterion("imomo >=", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoLessThan(String value) {
            addCriterion("imomo <", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoLessThanOrEqualTo(String value) {
            addCriterion("imomo <=", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoLike(String value) {
            addCriterion("imomo like", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoNotLike(String value) {
            addCriterion("imomo not like", value, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoIn(List<String> values) {
            addCriterion("imomo in", values, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoNotIn(List<String> values) {
            addCriterion("imomo not in", values, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoBetween(String value1, String value2) {
            addCriterion("imomo between", value1, value2, "imomo");
            return (Criteria) this;
        }

        public Criteria andImomoNotBetween(String value1, String value2) {
            addCriterion("imomo not between", value1, value2, "imomo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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
}