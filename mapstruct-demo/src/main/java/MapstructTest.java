import com.cn.demo.mapstruct.UserMapStruct;
import com.cn.demo.pojo.dto.vo.UserVO;
import com.cn.demo.pojo.entity.UserDO;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yc
 */
public class MapstructTest {
    public static void main(String[] args) {

        // DO -> VO
        UserDO userDO = new UserDO(1L, "zhansgan", "123456", "张三", 12, "13188888888", "123456@qq.com", DateTime.now().toDate(), DateTime.now().toDate());
        System.out.println("userDO = " + userDO);
        UserVO userVO = UserMapStruct.INSTANCE.doToVo(userDO);
        System.out.println("userVO = " + userVO);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        // VO -> DO
        UserVO userVO1 = new UserVO(1L, "张三", 18, "13166666666", "987654@qq.com", DateTime.now().toDate());
        System.out.println("userVO1 = " + userVO1);
        UserDO userDO1 = UserMapStruct.INSTANCE.voToDo(userVO1);
        System.out.println("userDO1 = " + userDO1);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        // DOList -> VOList
        List<UserDO> userDOList = new ArrayList<>();
        for (long i = 1; i <= 5; i++) {
            userDO.setId(i);
            userDOList.add(userDO);
        }
        System.out.println("userDOList = " + userDOList);
        List<UserVO> userVOList = UserMapStruct.INSTANCE.doToVo(userDOList);
        System.out.println("userVOList = " + userVOList);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

        // VOList -> DOList
        List<UserVO> userVOList1 = new ArrayList<>();
        for (long i = 0; i <= 5; i++) {
            userVO1.setId(i);
            userVOList1.add(userVO1);
        }
        System.out.println("userVOList1 = " + userVOList1);
        List<UserDO> userDOList1 = UserMapStruct.INSTANCE.voToDo(userVOList1);
        System.out.println("userDOList1 = " + userDOList1);

    }
}
