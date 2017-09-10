package com.jylu.study.algorithm;


/**
 * @author Lujiayun
 *
 */
public class ObjectSort {

	public static void main(String[] args) {
		Student student1 = new Student(3101, "lujiayun", "男", 22);
		Student student2 = new Student(1022, "jylu", "男", 21);
		Student student3 = new Student(1123, "ronaldo", "男", 23);
		Student student4 = new Student(2124, "didi", "女", 29);
		Student student5 = new Student(1005, "wmd", "女", 28);
		Student[] stuArray = { student1, student2, student3, student4, student5 };

		int min = 0;
		Student temp = null;
		for (int i = 0; i < stuArray.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < stuArray.length; j++) {
				if (stuArray[j].getStuNo() < stuArray[min].getStuNo()) {
					min = j;
				}
			}
			temp = stuArray[i];
			stuArray[i] = stuArray[min];
			stuArray[min] = temp;
		}
		for (Student student : stuArray) {
			System.out.println(student.toString());
		}
	}

}

class Student {
	private int stuNo;
	private String stuName;
	private String stuSex;
	private int stuAge;

	public Student(int stuNo, String stuName, String stuSex, int stuAge) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuAge = stuAge;
	}

	public Student() {
	}

	public int getStuNo() {
		return stuNo;
	}

	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public int getStuAge() {
		return stuAge;
	}

	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}

	@Override
	public String toString() {
		return "Student [stuNo=" + stuNo + ", stuName=" + stuName + ", stuSex=" + stuSex + ", stuAge=" + stuAge + "]";
	}

}
