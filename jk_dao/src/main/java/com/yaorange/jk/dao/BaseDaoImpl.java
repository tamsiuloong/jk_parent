package com.yaorange.jk.dao;



import com.yaorange.jk.utils.Pagination;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 泛型dao ：把所有dao公共的操作封装起来
 * @param <T>  当前持久化类
 * @param <ID> 当前持久化类主键类型
 */
@Repository
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	/**
	 * <保存实体> <完整保存实体>
	 *
	 * @param t
	 *            实体参数
	 */
	@Override
	public void save(T t) {
		this.getSession().save(t);
	}

	/**
	 * <保存或者更新实体>
	 *
	 * @param t
	 *            实体
	 */
	@Override
	public void saveOrUpdate(T t) {
		this.getSession().saveOrUpdate(t);
	}

	/**
	 * <load> <加载实体的load方法>
	 *
	 *
	 * @param entityClass
	 * @param id
	 *            实体的id
	 * @return 查询出来的实体
	 */
	@Override
	public T load(Class<T> entityClass, ID id) {
		T load = (T) this.getSession().load(entityClass, id);
		return load;
	}

	/**
	 * <get> <查找的get方法>
	 *
	 * @param entityClass
	 * @param id
	 *            实体的id
	 * @return 查询出来的实体
	 */
	@Override
	public T get(Class<T> entityClass, ID id) {
		T load = (T) this.getSession().get(entityClass, id);
		return load;
	}

	/**
	 * <contains>
	 *
	 * @param t
	 *            实体
	 * @return 是否包含
	 */
	@Override
	public boolean contains(T t) {
		return this.getSession().contains(t);
	}

	/**
	 * <delete> <删除表中的t数据>
	 *
	 * @param t
	 *            实体
	 */
	@Override
	public void delete(T t) {
		this.getSession().delete(t);
	}

	/**
	 * <根据ID删除数据>
	 *
	 *
	 * @param entityClass
	 * @param Id
	 *            实体id
	 * @return 是否删除成功
	 */
	@Override
	public boolean deleteById(Class<T> entityClass, ID Id) {
		T t = get(entityClass, Id );
		if (t == null) {
			return false;
		}
		delete(t);
		return true;
	}

	/**
	 * <删除所有>
	 *
	 * @param entities
	 *            实体的Collection集合
	 */
	@Override
	public void deleteAll(Collection<T> entities) {
		for (Object entity : entities) {
			this.getSession().delete(entity);
		}
	}

	/**
	 * <执行Hql语句>
	 *
	 * @param hqlString
	 *            hql  update User set name = ? ,age =?    德华  18
	 * @param values
	 *            不定参数数组
	 */
	@Override
	public void executeHql(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.executeUpdate();
	}

	/**
	 * <执行Sql语句>
	 *
	 * @param sqlString
	 *            sql
	 * @param values
	 *            不定参数数组
	 */
	@Override
	public void executeSql(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.executeUpdate();
	}

	/**
	 * <根据HQL语句查找唯一实体>
	 *
	 * @param hqlString
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询实体
	 */
	@Override
	public T getByHQL(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	/**
	 * <根据SQL语句查找唯一实体>
	 *
	 * @param sqlString
	 *            SQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询实体
	 */
	@Override
	public T getBySQL(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	/**
	 * <根据HQL语句，得到对应的list>
	 *
	 * @param hqlString
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询多个实体的List集合
	 */
	@Override
	public List<T> getListByHQL(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	/**
	 * <根据SQL语句，得到对应的list>
	 *
	 * @param sqlString
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询多个实体的List集合
	 */
	@Override
	public List<T> getListBySQL(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	/**
	 * 由sql语句得到List
	 *
	 * @param sql
	 * @param map
	 * @param values
	 * @return List
	 */
	@Override
	public List findListBySql(final String sql, final RowMapper map,
							  final Object... values) {
		final List list = new ArrayList();
		// 执行JDBC的数据批量保存
		Work jdbcWork = new Work() {
			public void execute(Connection connection) throws SQLException {

				PreparedStatement ps = null;

				ResultSet rs = null;
				try {
					ps = connection.prepareStatement(sql);

					for (int i = 0; i < values.length; i++) {
						setParameter(ps, i, values[i]);
					}

					rs = ps.executeQuery();
					int index = 0;
					while (rs.next()) {
						Object obj = map.mapRow(rs, index++);
						list.add(obj);

					}
				} finally {
					if (rs != null) {
						rs.close();

					}
					if (ps != null) {
						ps.close();
					}
				}
			}
		};
		this.getSession().doWork(jdbcWork);
		return list;
	}

	/**
	 * <refresh>
	 *
	 * @param t
	 *            实体
	 */
	@Override
	public void refresh(T t) {
		this.getSession().refresh(t);
	}

	/**
	 * <update>
	 *
	 * @param t
	 *            实体
	 */
	@Override
	public void update(T t) {
		this.getSession().update(t);
	}

	/**
	 * <根据HQL得到记录数>
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 记录总数
	 */
	@Override
	public Long countByHql(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if(values[i]!=null&&!values[i].equals("")){
					query.setParameter(i, values[i]);
				}
			}
		}
		return (Long) query.uniqueResult();
	}




	@Override
	public Pagination pageByHql(String hql, Integer pageNo, Integer pageSize, Object... values) {

		//截取统计hql
		int beginIndex = hql.indexOf("from");
		String countHql = "select count(*) "+ hql.substring(beginIndex);

		//查询数据库总记录
		Long totalCount = countByHql(countHql, values);

		//分页对象（基本分页信息  and  list数据）
		Pagination result = new Pagination(pageNo,pageSize,totalCount.intValue());

		Query query = getSession().createQuery(hql);

		if(values !=null){
			for(int i=0 ;i <values.length;i++){
				if(values[i]!=null&&!values[i].equals("")){
					query.setParameter(i, values[i]);
				}
			}
		}
		query.setFirstResult(result.getFirstResult());
		query.setMaxResults(result.getPageSize());
		//list数据
		List<T> list =query.list();
		result.setList(list);

		return result;
	}



	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 *
	 * @return session
	 */
	public Session getSession() {
		// 需要开启事物，才能得到CurrentSession
		// return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}

	/**
	 *
	 * 设置每行批处理参数
	 *
	 * @param ps
	 * @param pos
	 *            ?占位符索引，从0开始
	 * @param data
	 * @throws SQLException
	 * @see [类、类#方法、类#成员]
	 */
	private void setParameter(PreparedStatement ps, int pos, Object data)
			throws SQLException {
		if (data == null) {
			ps.setNull(pos + 1, Types.VARCHAR);
			return;
		}
		Class dataCls = data.getClass();
		if (String.class.equals(dataCls)) {
			ps.setString(pos + 1, (String) data);
		} else if (boolean.class.equals(dataCls)) {
			ps.setBoolean(pos + 1, ((Boolean) data));
		} else if (int.class.equals(dataCls)) {
			ps.setInt(pos + 1, (Integer) data);
		} else if (double.class.equals(dataCls)) {
			ps.setDouble(pos + 1, (Double) data);
		} else if (Date.class.equals(dataCls)) {
			Date val = (Date) data;
			ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
		} else if (BigDecimal.class.equals(dataCls)) {
			ps.setBigDecimal(pos + 1, (BigDecimal) data);
		} else {
			// 未知类型
			ps.setObject(pos + 1, data);
		}

	}

	@Override
	public Pagination page(Integer page, Integer pageSize, DetachedCriteria criteria) {


		criteria.setProjection(Projections.rowCount());
		List<Long> totalCountList = (List<Long>) hibernateTemplate.findByCriteria(criteria);

		Long totalCount = 0L;
		if(totalCountList!=null && totalCountList.size()>0){
			totalCount= totalCountList.get(0);
		}
//		Long totalCount = 10L;
		Pagination result=new Pagination(page,pageSize,totalCount.intValue());

		criteria.setProjection(null);

		List<T> list = (List<T>) hibernateTemplate.findByCriteria(criteria,result.getFirstResult(),result.getPageSize());

		result.setList(list);

		return result;
	}

	@Override
	public void evict(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.evict(t);
	}

	@Override
	public void merge(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(t);
	}

	public static void main(String[] args) {
		//如何将 select u.name from User u  --->select count(*)  from User u
		String hql = "select u.name from User u where u.name = ?";
		int beginIndex = hql.indexOf("from");
		String countHql = "select count(*) "+ hql.substring(beginIndex);
		System.out.println(countHql);
	}
}
