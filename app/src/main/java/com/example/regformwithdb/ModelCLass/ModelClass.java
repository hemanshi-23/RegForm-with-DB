package com.example.regformwithdb.ModelCLass;

public class ModelClass {

    private String StudentName;
    private String StudentEmail;
    private String StudentPass;
    private int id;

    public ModelClass(String StudentName,String StudentEmail,String StudentPass)
    {
        this.StudentName = StudentName;
        this.StudentEmail = StudentEmail;
        this.StudentPass = StudentPass;
    }

    public String getStudentName() {
        return StudentName;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public String getStudentPass() {
        return StudentPass;
    }

    public int getId()
    { return id; }

    public void setId(int id)
    { this.id = id; }

}
