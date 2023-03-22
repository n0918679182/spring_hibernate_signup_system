package com.signSysHibernate.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.signSysHibernate.entity.SignUpEntity;


@Repository
public class SignUpDao {	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(SignUpDao.class);
	
	//根據id查詢單筆
	public SignUpEntity getStudent(String sno) {
		return sessionFactory.getCurrentSession().get(SignUpEntity.class, sno);
	}
	
	public SignUpEntity getStudentByMail(String smail) {
		 List<SignUpEntity> allStuds = queryAllStudents();
		 SignUpEntity s = allStuds.stream().filter(o->o.getSmail().equals(smail)).findFirst().get();
		 return s;
	}
	
	// 查詢全部
	public List<SignUpEntity> queryAllStudents() {
		Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery < SignUpEntity > cq = cb.createQuery(SignUpEntity.class);
        Root < SignUpEntity > root = cq.from(SignUpEntity.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
	}
	
	// 新增
	public void add(SignUpEntity s) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(s);
		logger.info("Person saved successfully, Person Details="+s);
	}
	
	// 修改
	public void update(SignUpEntity s) {
		Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(s);
	}
}
