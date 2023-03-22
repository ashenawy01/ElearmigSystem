package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Week implements Serializable {

    // to ensure that the same class is used for serialization and deserialization
    private static final long serialVersionUID = 1L;

    int weekNum; // week number
    List<String> fileSrcList; // files sources


    // constructor to initialize the all attributes
    public Week (int num, String... files) {
        fileSrcList = new ArrayList<>(List.of(files));
        this.weekNum = num;
    }
    // overloading constructor to create a week with no files
    public Week (int num) {
        this.weekNum = num;
        fileSrcList = new ArrayList<>();
    }

    // Setters and getters
    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public List<String> getFileSrcList() {
        return fileSrcList;
    }

    public void setFileSrcList(List<String> fileSrcList) {
        this.fileSrcList = fileSrcList;
    }

    // add and remove a file
    // returns true or false
    public boolean addFile (String file)  {
        return fileSrcList.add(file);
    }
    public boolean removeFile (String file)  {
       return fileSrcList.remove(file);
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("Week No. " + weekNum + "\nFiles : \n");
       for (int i = 0; i < fileSrcList.size(); i++) {
           stringBuilder.append(fileSrcList.get(i) + "\n");
       }
       return stringBuilder.toString();
    }
}
