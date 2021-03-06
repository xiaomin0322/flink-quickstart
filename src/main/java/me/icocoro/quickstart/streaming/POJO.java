package me.icocoro.quickstart.streaming;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * 业务数据封装的实体类
 * 和Spark相比，社区版的Flink就是个半成品，修修补补自定义，根本上不了生产
 */
public class POJO implements Serializable {
    private static final long serialVersionUID = -4281155263332879073L;
    private String aid;
    private String astyle;
    private String aname;
    private Long logTime;
    private BigDecimal energy;
    private Integer age;
    private Date tt;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAstyle() {
        return astyle;
    }

    public void setAstyle(String astyle) {
        this.astyle = astyle;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Long getLogTime() {
        return logTime;
    }

    public void setLogTime(Long logTime) {
        this.logTime = logTime;
    }

    public BigDecimal getEnergy() {
        return energy;
    }

    public void setEnergy(BigDecimal energy) {
        this.energy = energy;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getTt() {
        return tt;
    }

    public void setTt(Date tt) {
        this.tt = tt;
    }

    @Override
    public String toString() {
        return "POJO{" +
                "aid='" + aid + '\'' +
                ", astyle='" + astyle + '\'' +
                ", aname='" + aname + '\'' +
                ", logTime=" + logTime +
                ", energy=" + energy +
                ", age=" + age +
                ", tt=" + tt +
                '}';
    }
}
