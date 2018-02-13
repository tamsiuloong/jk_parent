package com.yaorange.jk.dao;

import com.yaorange.jk.entity.Module;
import com.yaorange.jk.utils.Pagination;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @param <T> 持久化类
 * @param <ID> 持久化类主键类型
 */
public interface BaseDao<T, ID extends Serializable> {
    /**
     * <保存实体>
     * <完整保存实体>
     * @param t 实体参数
     */
    public abstract void save(T t);

    /**
     * <保存或者更新实体>
     * @param t 实体
     */
    public abstract void saveOrUpdate(T t);

    /**
     * <load>
     * <加载实体的load方法>
     *
     * @param entityClass
     * @param id 实体的id
     * @return 查询出来的实体
     */
    public abstract T load(Class<T> entityClass, ID id);

    /**
     * <get>
     * <查找的get方法>
     * @param entityClass
     * @param id 实体的id
     * @return 查询出来的实体
     */
    public abstract T get(Class<T> entityClass, ID id);

    /**
     * <contains>
     * @param t 实体
     * @return 是否包含
     */
    public abstract boolean contains(T t);

    /**
     * <delete>
     * <删除表中的t数据>
     * @param t 实体
     */
    public abstract void delete(T t);

    /**
     * <根据ID删除数据>
     *
     * @param entityClass
     * @param Id 实体id
     * @return 是否删除成功
     */
    public abstract boolean deleteById(Class<T> entityClass, ID Id);

    /**
     * <删除所有>
     * @param entities 实体的Collection集合
     */
    public abstract void deleteAll(Collection<T> entities);

    /**
     * <执行Hql语句>
     * @param hqlString hql
     * @param values 不定参数数组
     */
    public abstract void executeHql(String hqlString, Object... values);

    /**
     * <执行Sql语句>
     * @param sqlString sql
     * @param values 不定参数数组
     */
    public abstract void executeSql(String sqlString, Object... values);

    /**
     * <根据HQL语句查找唯一实体>
     * @param hqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询实体
     */
    public abstract T getByHQL(String hqlString, Object... values);

    /**
     * <根据SQL语句查找唯一实体>
     * @param sqlString SQL语句
     * @param values 不定参数的Object数组
     * @return 查询实体
     */
    public abstract T getBySQL(String sqlString, Object... values);

    /**
     * <根据HQL语句，得到对应的list>
     * @param hqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询多个实体的List集合
     */
    public abstract List<T> getListByHQL(String hqlString, Object... values);
    /**
     * <根据SQL语句，得到对应的list>
     * @param sqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询多个实体的List集合
     */
    public abstract List<T> getListBySQL(String sqlString, Object... values);

    /**
     * 由sql语句得到List
     * @param sql
     * @param map
     * @param values
     * @return List
     */
    public List findListBySql(final String sql, final RowMapper map, final Object... values);

    /**
     * <refresh>
     * @param t 实体
     */
    public abstract void refresh(T t);

    /**
     * <update>
     * @param t 实体
     */
    public abstract void update(T t);

    /**
     * <根据HQL得到记录数>
     * @param hql HQL语句
     * @param values 不定参数的Object数组
     * @return 记录总数
     */
    public abstract Long countByHql(String hql, Object... values);

    /**
     * 分页查询
     * @param hql    hql
     * @param pageNo 第几页
     * @param pageSize 每页多少条数据
     * @param values    hql可变参数
     * @return
     */
    public abstract Pagination pageByHql(String hql, Integer pageNo, Integer pageSize, Object... values);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param criteria
     * @return
     */
    Pagination page(Integer page, Integer pageSize, DetachedCriteria criteria);

    /**
     * 在hibernate一级缓存中清除一个对象
     * @param module
     */
    void evict(T module);
}
