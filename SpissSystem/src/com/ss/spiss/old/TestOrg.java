package com.ss.spiss.old;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <pre>
 *     If you annotate your Artifact class with the annotation:
 *  @XmlAccessorType(XmlAccessType.FIELD)
 *  then you do not need to annotate the fields with @XmlElement and the
 *  setter/getter methods will be ignored.
 * </pre>
 * 
 * @author User
 */
@XmlAccessorType(XmlAccessType.FIELD)
// 用了这个之后就会自动忽略setter/getter方法。不用这个就需要去掉注解，需要保证属性名和xml里的表签名一致
public class TestOrg {
    @XmlElement(name = "org_id")
    private Long orgId;
    @XmlElement(name = "parent_id")
    private Long parentId;
    @XmlElement(name = "org_name")
    private String orgName;
    @XmlElement(name = "org_code")
    private String orgCode;
    @XmlElement(name = "org_type")
    private String orgType;
    @XmlElement(name = "start_d")
    private Date startDate;
    @XmlElement(name = "end_d")
    private Date endDate;
    @XmlElement(name = "attribute1")
    private String attribute;
    @XmlElement(name = "insert_t")
    private Date insertTime;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

}