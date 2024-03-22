package com.renshuo.cloud.util;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Sets;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Lenovo on 2023/10/8.
 */
public class NetworkInfoUtil {

    public static String getNetworkInfo() {

        String address = "";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    byte[] mac = networkInterface.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        System.out.println("MAC Address: " + sb.toString());
                        address = sb.toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return address;

    }

    public static int calculateLimit(int pageNumber, int pageSize, int totalRecords) {
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (pageNumber < 1 || pageNumber > totalPages) {
            return 0; // 页码不合法，返回0表示不执行查询
        }
        if (pageNumber == totalPages) {
            return totalRecords % pageSize; // 最后一页的条数可能不足pageSize
        }
        return pageSize;
    }


    public static int calculatePageSize(int totalRecords, int pageNumber) {
        if (totalRecords <= 0 || pageNumber <= 0) {
            return 0;
        }
        // 假设希望每页显示的记录数固定为 10
        int desiredPageSize = 10;
        return (int) Math.ceil((double) totalRecords / ((pageNumber - 1) * desiredPageSize));
    }

    //做个测试
    static int recur(int n) {
        // 终止条件
        if (n == 1)
            return 1;
        // 递：递归调用
        int res = recur(n - 1);
        // 归：返回结果
        return n + res;
    }

    /* 尾递归 */
    static int tailRecur(int n, int res) {
        // 终止条件
        if (n == 0)
            return res;
        // 尾递归调用
        return tailRecur(n - 1, res + n);
    }


    /* 斐波那契数列：递归 */
    static int fib(int n) {
        // 终止条件 f(1) = 0, f(2) = 1
        if (n == 1 || n == 2)
            return n - 1;
        // 递归调用 f(n) = f(n-1) + f(n-2)
        int res = fib(n - 1) + fib(n - 2);
        // 返回结果 f(n)
        return res;
    }

    private static String getInterNo(String vin) {
        String interNo = null;
        int count = 0;
        String suffix = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int forNumber = 100;
        String prex = "";
        while (count < forNumber) {
            String randomNo = prex + vin.substring(vin.length() - 7);
//            int i = geelyMapper.countInterNo(randomNo);
            if (false) {
                interNo = randomNo;
                break;
            } else {
                if (count >= 26) {
                    prex = RandomStringUtils.random(2, suffix);
                } else {
                    prex = suffix.charAt(count) + "";
                }
                count++;
                interNo = prex;
            }

        }
        return interNo;
    }

    static int exponential(int n) {
        int count = 0, base = 1;
        // 细胞每轮一分为二，形成数列 1, 2, 4, 8, ..., 2^(n-1)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < base; j++) {
                count++;
            }
            base *= 2;
        }
        // count = 1 + 2 + 4 + 8 + .. + 2^(n-1) = 2^n - 1
        return count;
    }

    /* 指数阶（递归实现） */
    static int expRecur(int n) {
        if (n == 1)
            return 1;
        return expRecur(n - 1) + expRecur(n - 1) + 1;
    }

    /* 删除数组指定元素 */
    static int[] delArrays(int[] num, int index) {
        int[] res = new int[num.length - 1];
        for (int i = 0; i < index; i++) {
            res[i] = num[i];
        }
        for (int i = index; i < num.length - 1; i++) {
            res[i] = num[i + 1];
        }
        return res;
    }

    public static void main(String[] args) {

        Set<String> groups = Sets.newHashSet();

        String [] ruleInfos =new String[]{"A","B","C"};
        boolean isAllList=true;
        for (String str: ruleInfos){
            if ((!"A".equals(str)) || groups.contains(str)){
                isAllList = false;
                break;
            }
            groups.add(str);
        }
        System.out.println(isAllList);


        AtomicReference<Boolean> bool = new AtomicReference<>();
        AtomicReference<String> namesAtomicReference = new AtomicReference<>();
        List<String> nameList = new ArrayList<>();
        nameList.add("张三");
        nameList.add("李四");
        nameList.add("王五");

//在nameList.forEach过程中，为nameList中每个元素，添加一个“;”
        nameList.forEach(name->{
            String str = "";
            if(ObjectUtil.isEmpty(namesAtomicReference.get())){
                str = name+";";
            }
            else{
                str = namesAtomicReference.get()+name+";";
            }
            namesAtomicReference.set(str);
        });

        String strNames = namesAtomicReference.toString();
        StringBuilder names = new StringBuilder(strNames);
        names.replace(strNames.length()-1,strNames.length(),"");
        System.out.println("姓名为：{"+names+"}");


        //hello-algo
        //5.1---栈操作
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.push(5);
//
//        System.out.println(stack.peek());
//        System.out.println(stack.pop());
//        System.out.println(stack.size());
//        System.out.println(stack.empty());
//
//
//        LinkedListStack<String> sk = new LinkedListStack<>();
//        sk.push("a");
//        sk.push("b");
//        sk.push("c");
//        sk.push("d");
//        sk.push("e");
//        System.out.println(sk.peek());
//        System.out.println(sk.pop());
//        System.out.println(sk.size());
//
//
//        ArrayStack<String> ak = new ArrayStack<>();
//        ak.push("a");
//        ak.push("b");
//        ak.push("c");
//        ak.push("d");
//        ak.push("e");
//        System.out.println(ak.peek());
//        System.out.println(ak.pop());
//        System.out.println(ak.size());

        //5.2 队列----队列操作
//        Queue<Integer> q = new LinkedList<>();
//        q.offer(1);
//        q.offer(2);
//        q.offer(3);
//        q.offer(4);
//        q.offer(5);
//
//        System.out.println(q.peek());
//        System.out.println(q.poll());
//        System.out.println(q.size());


//        LinkedListQueue q = new LinkedListQueue();
//        q.push(1);
//        q.push(2);
//        q.push(3);
//        q.push(4);
//        q.push(5);
//
//        System.out.println(q.peek());
//        System.out.println(q.pop());

        //5.3双向队列
//        Deque<Integer> d = new LinkedList<>();
//        d.offerFirst(1);
//        d.offerFirst(2);
//        d.offerLast(3);
//        d.offerLast(4);
//
//        System.out.println(d.peekFirst());
//        System.out.println(d.peekLast());
//        System.out.println(d.pollFirst());
//        System.out.println(d.peekFirst());
//
//        System.out.println(d.pollLast());

//        LinkedListDeque d = new LinkedListDeque();
//        d.pushFirst(1);
//        d.pushFirst(2);
//        d.pushFirst(3);
//        d.pushFirst(6);
//        d.pushFirst(7);
//        d.pushLast(4);
//        d.pushLast(5);
//        System.out.println(d.peekFirst());
//        System.out.println(d.peekLast());


//        6.1.2   哈希表简单实现

//        ArrayHashMap map = new ArrayHashMap();
//        map.put(1,"1");
//        map.put(2,"2");
//        map.put(3,"3");
//        map.put(4,"4");
//
//        System.out.println(map.get(1));
//
//        System.out.println(map.pairSet());
//        map.print();

//        6.2.1   链式地址

//        HashMapChaining map = new HashMapChaining();
//        map.put(1,"1");
//        map.put(11,"11");
//        map.put(2,"2");
//        map.put(3,"3");
//        map.put(4,"4");
//        map.put(22,"22");
//
//        System.out.println(map.get(11));


        //6.2.2   开放寻址
//        HashMapOpenAddressing map =new HashMapOpenAddressing();
//        map.put(1, "1");
//        map.put(11, "11");
//        map.put(2, "2");
//        map.put(3, "3");
//        map.put(4, "4");
//        map.put(22, "22");
//        map.remove(22);
//        map.print();


//        int num = 3;
//        int hashNum = Integer.hashCode(num);
//        System.out.println(hashNum);
//
//        boolean bool = true;
//        int hashBool = Boolean.hashCode(bool);
//        System.out.println(hashBool);
//
//        String str = "Hello";
//        int hashStr = str.hashCode();
//        System.out.println(hashStr);
//
//        double d = 3.14159;
//        int i1 = Double.hashCode(d);
//        System.out.println(i1);
//
//        Object [] arr = new String[]{"1","2"};
//
//        int i2 = Arrays.hashCode(arr);
//        System.out.println(i2);
//
//        ListNode obj = new ListNode(0);
//        int i3 = obj.hashCode();
//        System.out.println(i3);

        //7.1.2   二叉树基本操作

        TreeNode p = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(3);
        TreeNode l2 = new TreeNode(4);
        TreeNode r2 = new TreeNode(5);
        TreeNode l3 = new TreeNode(6);


        p.setLeftNode(l1);
        p.setRightNode(r1);

        l1.setLeftNode(l2);
        l1.setRightNode(r2);

        l2.setLeftNode(l3);

        //2.   插入与删除节点
        TreeNode del = new TreeNode(0);
        p.setLeftNode(del);
        del.setLeftNode(l1);




//        int[] ints = delArrays(new int[]{1, 2, 3, 4, 5, 6}, 2);
//        for (int i = 0; i < ints.length; i++) {
//            System.out.println(ints[i]);
//        }


        /* 初始化列表 */
        // 无初始值
//        List<Integer> nums1 = new ArrayList<>();
//        // 有初始值（注意数组的元素类型需为 int[] 的包装类 Integer[]）
//        Integer[] numbers = new Integer[]{1, 3, 2, 5, 4};
//        List<Integer> nums = new ArrayList<>(Arrays.asList(numbers));

//        System.out.println(getInterNo("LA71AUB18P0510350"));
//        int recur = recur(4);
//        int recur = tailRecur(4,0);
//        System.out.println(recur);
//        System.out.println(fib(6));

//        System.out.println(exponential(3));
//        System.out.println(expRecur(3));
//
//
//        String[] arrs = {"1", "2"};

//        try {
//            NetworkInfoUtil ni = new NetworkInfoUtil("192.168.20.217");
//            System.out.println(ni.GetRemoteMacAddr());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        int total =1021;
//        //1、固定页数，计算出每页的条数
//        int pageTotal = 20;
//        int pageSize = total / pageTotal;
//
//        int lastPageSize =pageSize;
//        if(total % pageTotal!=0){
//            lastPageSize +=(total % pageTotal)-1;
//        }
//
//        for (int i = 0; i < 20; i++) {
//            if(i<19) {
//                System.out.println("第" + i + "页，查询条数是" + pageSize);
//            }else{
//                System.out.println("第" + i + "页，查询条数是" + lastPageSize);
//            }
//
//        }


//        int total =10001;
//        //1、固定页数，计算出每页的条数
//        int pageTotal = 20;
//        int pageSize = total / pageTotal;
//        if(total % pageTotal!=0){
//            pageSize+=1;
//        }
//        int lastPageSize = total - (pageSize*19);
//
//        for (int i = 0; i < 20; i++) {
//            if (i < 19) {
//                System.out.println("第" + i + "页，查询条数是" + pageSize);
//            } else {
//
//                System.out.println("最后" + i + "页，查询条数是" + lastPageSize);
//            }
//
//        }


//        int totalRecords = 100;       // 总记录条数
//        int pageNumber = 5;          // 固定页数
//
//        int pageSize = calculatePageSize(totalRecords, pageNumber); // 计算合理的每页条数
//        System.out.println(pageSize+"----"+pageSize);


        String underscoreStr = "hello_world_how_are_you";
        String camelCaseStr = "";

        String name = underscoreStr.substring(underscoreStr.indexOf("_") + 1);

        String name2 = underscoreStr.substring(0, underscoreStr.indexOf("_"));
        System.out.println(name + ":" + name2);


        String[] words = name.split("_"); // 按照下划线分割字符串
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                camelCaseStr += word;
                continue;
            }
            if (word.length() > 0) { // 避免处理空字符串
                camelCaseStr += word.substring(0, 1).toUpperCase() + word.substring(1);
            }
        }

        System.out.println(camelCaseStr); // 输出驼峰格式的字符串
    }

    /**
     * 二叉树的层序遍历,1.   代码实现
     * @param parent
     * @return
     */
    List<Integer> levelOrder(TreeNode parent){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(parent);

        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()){

            TreeNode node = q.poll();
            res.add(node.getVal());
            if(node.getLeftNode()!=null){
                q.offer(node.getLeftNode());
            }
            if(node.getRightNode()!=null){
                q.offer(node.getRightNode());
            }
        }
        return res;

    }


    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }


    private String sRemoteAddr;
    private int iRemotePort = 137;
    private byte[] buffer = new byte[1024];
    private DatagramSocket ds = null;

    public NetworkInfoUtil(String strAddr) throws Exception {
        sRemoteAddr = strAddr;
        ds = new DatagramSocket();
    }

    protected final DatagramPacket send(final byte[] bytes) throws IOException {
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(sRemoteAddr), iRemotePort);
        ds.send(dp);
        return dp;
    }

    protected final DatagramPacket receive() throws Exception {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp);
        return dp;
    }

    protected byte[] GetQueryCmd() throws Exception {
        byte[] t_ns = new byte[50];
        t_ns[0] = 0x00;
        t_ns[1] = 0x00;
        t_ns[2] = 0x00;
        t_ns[3] = 0x10;
        t_ns[4] = 0x00;
        t_ns[5] = 0x01;
        t_ns[6] = 0x00;
        t_ns[7] = 0x00;
        t_ns[8] = 0x00;
        t_ns[9] = 0x00;
        t_ns[10] = 0x00;
        t_ns[11] = 0x00;
        t_ns[12] = 0x20;
        t_ns[13] = 0x43;
        t_ns[14] = 0x4B;

        for (int i = 15; i < 45; i++) {
            t_ns[i] = 0x41;
        }

        t_ns[45] = 0x00;
        t_ns[46] = 0x00;
        t_ns[47] = 0x21;
        t_ns[48] = 0x00;
        t_ns[49] = 0x01;
        return t_ns;
    }

    protected final String GetMacAddr(byte[] brevdata) throws Exception {

        int i = brevdata[56] * 18 + 56;
        String sAddr = "";
        StringBuffer sb = new StringBuffer(17);

        for (int j = 1; j < 7; j++) {
            sAddr = Integer.toHexString(0xFF & brevdata[i + j]);
            if (sAddr.length() < 2) {
                sb.append(0);
            }
            sb.append(sAddr.toUpperCase());
            if (j < 6) sb.append(':');
        }
        return sb.toString();
    }

    public final void close() {
        try {
            ds.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public final String GetRemoteMacAddr() throws Exception {
        byte[] bqcmd = GetQueryCmd();
        send(bqcmd);
        DatagramPacket dp = receive();
        String smac = GetMacAddr(dp.getData());
        close();

        return smac;
    }

}
