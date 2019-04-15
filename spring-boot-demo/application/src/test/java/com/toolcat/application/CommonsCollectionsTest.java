package com.toolcat.application;

/**
 * https://www.cnblogs.com/myitnews/p/12289816.html
 */

public class CommonsCollectionsTest {
    /**
     * *********** 常用类  ***********
     * 1. org.apache.commons.collections4.CollectionUtils
     *      isEmpty 判断集合是否为空
     *      isNotEmpty 判断集合不为空
     *      isEqualCollection 比较两集合值是否相等, 不考虑元素的顺序
     *      union 并集, 不会去除重复元素
     *      intersection 交集
     *      disjunction 交集的补集
     *      subtract 差集, 不去重
     *      unmodifiableCollection 得到一个集合镜像，不允许修改，否则报错
     *      containsAny 判断两个集合是否有相同元素
     *      getCardinalityMap 统计集合中各元素出现的次数，并以Map<Object, Integer>输出
     *      isSubCollection a是否 b 的子集合, a集合大小 <= b集合大小
     *      isProperSubCollection a是否 b 的子集合, a集合大小 < b集合大小
     *      cardinality 某元素在集合中出现的次数
     *      find 返回集合中满足函数式的唯一元素，只返回最先处理符合条件的唯一元素, 以废弃
     *      filter 过滤集合中满足函数式的所有元素
     *      transform 转换新的集合，对集合中元素进行操作，如每个元素都累加1
     *      countMatches 返回集合中满足函数式的数量
     *      select 将满足表达式的元素存入新集合中并返回新集合元素对象
     *      selectRejected 将不满足表达式的元素存入新集合中并返回新集合元素对象
     *      collect  collect底层调用的transform方法, 将所有元素进行处理，并返回新的集合
     *      addAll  将一个数组或集合中的元素全部添加到另一个集合中
     *      get 返回集合中指定下标元素
     *      isFull 判断集合是否为空
     *      maxSize 返回集合最大空间
     *      predicatedCollection 只要集合中元素不满足表达式就抛出异常
     *      removeAll 删除集合的子集合
     *      synchronizedCollection 同步集合
     *
     * 2. org.apache.commons.collections4.MapUtils
     *      isEmpty 判断Map是否为空
     *      isNotEmpty 判断Map是否不为空
     *      getBoolean 从Map中获取 Boolean, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getBooleanValue 从Map中获取 boolean, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getDouble 从Map中获取 Double, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getDoubleValue 从Map中获取 double, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getFloat 从Map中获取 Float, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getFloatValue 从Map中获取 float, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getInteger 从Map中获取 Integer, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getIntegerValue 从Map中获取 int, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getLong 从Map中获取 Long, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getLongValue 从Map中获取 long, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getString 从Map中获取 String, 其重载方法有三个参数, 表示如果转换失败则使用默认值
     *      getMap 获取Map类型的值
     *      putAll 将二维数组放入Map中
     *
     */

}
