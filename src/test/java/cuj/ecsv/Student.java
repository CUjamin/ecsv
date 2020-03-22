package cuj.ecsv;

import cuj.ecsv.annotation.Column;

/**
 * @author cujamin
 * @date 2020/1/12
 */
public class Student {

    @Column(name = "NNNNNN")
    private String name;
    private boolean isStudent;
    private int age;
    private long id;
    private float score;
    private double dt;

    @Column(name = "NNNNNN")
    public String getName() {
        return name;
    }

    @Column(name = "NNNNNN")
    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", isStudent=" + isStudent +
                ", age=" + age +
                ", id=" + id +
                ", score=" + score +
                ", dt=" + dt +
                '}';
    }
}
