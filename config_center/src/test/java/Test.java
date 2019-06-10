
/**
 * Created on 2019-02-26 12:30
 * by @author JeramTough
 */
public class Test {

    public static void main(String[] args){
        for (int i=1;i<=50;i++){
            int id=i;
            String tableName="appraise_tb";
            String idName="appraise_id";
            String sql="UPDATE "+tableName+" SET create_time=(select * FROM (select date_sub" +
                    "((SELECT create_time FROM "+tableName+" WHERE "+idName+"="+id+"), " +
                    "interval '1' YEAR) AS abc) as b) WHERE "+idName+"="+id+";";

//            L.p(sql);
        }
    }

    public void test2(){

    }

}
