/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cr.hr.action.adver.collect;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.moon.common.db.SQLTool;
import org.moon.common.util.ChinaTransCode;
import org.moon.s2sh.action.util.BaseAction;
import org.moon.s2sh.service.GeneralService;

import com.cr.util.KeyUtil;

/**
 * <b>版权信息 :</b> 2012，云技术有限公司<br/>
 * <b>功能描述 :</b> <br/>
 * <b>版本历史 :</b> <br/>
 * @author 周小桥| 2014-6-18 下午7:07:56 | 创建
 */
public class ResumeAdminAction extends BaseAction
{

	/**
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	private GeneralService ds = new GeneralService();

	private int currPage;

	private int pageSize;

	private String sortname;

	private String sortorder;

	private String eid;

	private String china_name;

	private String sex;

	private String age;

	private String college;

	private String work_history;

	private String address;

	private String interest;

	private String graduate_time;

	private String professional;

	private String tel;

	private String born_date;

	private String adver_way;

	private String interview_person;

	private String interview_date;

	private String hope_salary;

	private String fact_salary;

	private String employ_status;

	private String qual_cert;

	private String interview_evaluate;
	
	private String come_date ;
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	public String add() throws Throwable
	{
		logger.info("add");
		HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		try
		{
			setPageParm(request);

			long id = KeyUtil.getLongID();
			String sql = "INSERT  INTO tab_advertise (eid,china_name,sex,work_history,age,employ_status,interview_person,interview_date,"
					+ "college,address,tel,born_date,adver_way,hope_salary,fact_salary,graduate_time,interview_evaluate,qual_cert,come_date,interest,professional) VALUES ('"
					+ id
					+ "','"
					+ china_name
					+ "','"
					+ sex
					+ "','"
					+ work_history
					+ "',"
					+ age
					+ ",'"
					+ employ_status
					+ "','"
					+ interview_person
					+ "','"
					+ interview_date
					+ "','"
					+ college
					+ "','"
					+ address
					+ "','"
					+ tel
					+ "','"
					+ born_date
					+ "','"
					+ adver_way
					+ "',"
					+ hope_salary
					+ ","
					+ fact_salary		
					+ ",'"
					+ graduate_time
					+ "','"
					+ interview_evaluate  
					+ "','"
					+ qual_cert		
					+ "','"
					+ come_date
					+ "','"
					+ interest + "','" +professional + "')";

			if (ds.insert(sql, null) > 0)
			{
				logger.info("插入成功！");
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public String update()
	{
		logger.info("update");
		HttpServletRequest request = ServletActionContext.getRequest();
		try
		{
			setPageParm(request);
			String u_sql = null;
			u_sql = SQLTool.appendUpdateSql(u_sql, "china_name", "String",
					china_name);
			u_sql = SQLTool
					.appendUpdateSql(u_sql, "college", "String", college);
			u_sql = SQLTool.appendUpdateSql(u_sql, "work_history", "String",
					work_history);
			u_sql = SQLTool.appendUpdateSql(u_sql, "age", "String", age);
			u_sql = SQLTool.appendUpdateSql(u_sql, "employ_status", "String",
					employ_status);
			u_sql = SQLTool.appendUpdateSql(u_sql, "graduate_time", "date",
					graduate_time);
			u_sql = SQLTool
					.appendUpdateSql(u_sql, "college", "String", college);
			u_sql = SQLTool.appendUpdateSql(u_sql, "professional", "String", ""
					+ professional);
			u_sql = SQLTool.appendUpdateSql(u_sql, "hope_salary", "String", ""
					+ hope_salary);
			u_sql = SQLTool.appendUpdateSql(u_sql, "interview_person", "date",
					interview_person);
			String sql = "update tab_employee set " + u_sql + " where eid="
					+ eid;
			if (ds.update(sql, null) > 0)
			{
				logger.info("更新成功！");
			}
		} catch (Exception e)
		{

			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void delete()
	{
		JSONObject msg = new JSONObject();
		logger.info("delete");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try
		{
			setPageParm(request);
			String did = request.getParameter("deletes");

			if (did != null)
			{
				did = did.replaceAll(",", "','");
				String sql = "DELETE FROM  tab_advertise where eid in ('" + did
						+ "')";
				int rs = ds.delete(sql, null);
				if (rs > 0)
				{
					msg.put("success", "删除" + rs + "条数据 成功！");
				}

			}
		} catch (Exception e)
		{
			e.printStackTrace();
			// return "error";
		} finally
		{
			this.doJsonResponse(response, msg);
		}

		// return "success";
	}

	public void editData()
	{
		logger.info("editData");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String keyID = request.getParameter("keyID");
		JSONObject jsonObj = null;

		try
		{
			logger.info("keyID==" + keyID);
			String sql = "SELECT u.*  from tab_advertise u,sec_org s where  u.eid="
					+ keyID  ;
			List<JSONObject> jl = ds.query(sql, null);
			// JSONObject data_ret = new JSONObject();
			jsonObj = jl.get(0);

		} catch (Exception e)
		{
			jsonObj.put("error", "查询失败！");
			logger.error(e);
			// return "error";
		} finally
		{
			this.doJsonResponse(response, jsonObj);
		}
		// return "edit";
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author 周小桥 |2014-6-18 下午7:06:56
	 * @version 0.1
	 */
	public void initPage()
	{
		logger.info("initPage");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		JSONObject jsonObj = new JSONObject();
		String sql = null;
		try
		{
			setPageParm(request);

			sql = request.getParameter("sql");
			if (sql == null || "".equals(sql) || "null".equals(sql))
				sql = "SELECT v.* from tab_advertise v";
			if (sortname != null && !"".equals(sortname))
			{
				jsonObj = ds.getPageQuery(sql, currPage, pageSize, sortname,
						sortorder);
			} else
				jsonObj = ds.getPageQuery(sql, currPage, pageSize, "eid",
						"desc");
			jsonObj.put("success", "查询成功！");

		} catch (Exception e)
		{
			jsonObj.put("error", "查询失败！");
			logger.error(e);
		} finally
		{
			this.doJsonResponse(response, jsonObj);
		}

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author 周小桥 |2014-6-27 下午5:15:07
	 * @version 0.1
	 */
	public String doFind()
	{
		logger.info("doFind");
		HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		String sql = "SELECT * from tab_employee ";
		setPageParm(request);
		if (request.getParameter("where") != null)
		{
			String whereSQL_print = request.getParameter("where");
			whereSQL_print = ChinaTransCode.getJspUTFSubmmit(whereSQL_print);
			sql = sql + whereSQL_print;
			// 转成打印传出条件语句
			whereSQL_print = whereSQL_print.replaceAll("=", ":");
			request.setAttribute("whereSQL_print", whereSQL_print);
		}
		request.setAttribute("sql", sql);

		return "success";
	}

	/**
	 * @param response
	 * @param JSONObj
	 * @author 周小桥 |2014-6-26 下午5:42:30
	 * @version 0.1
	 */
	private void doJsonResponse(HttpServletResponse response, JSONObject JSONObj)
	{
		// 设置字符编码
		response.setCharacterEncoding("UTF-8");
		// 返回json对象（通过PrintWriter输出）
		try
		{
			String key = "RESPCODE";
			if (!JSONObj.containsKey(key))
			{
				JSONObj.put(key, "0000");
			}

			String resp = (String) JSONObj.get(key);

			key = "RESPMSG";
			if (!"0000".equals(resp) && !JSONObj.containsKey(key))
			{

				JSONObj.put(key, "操作错误");
			}

			response.getWriter().print(JSONObj);
		} catch (IOException e)
		{

			logger.error("写JSON返回数据出错.");
			logger.error(e);
		}
	}

	/**
	 * @param request
	 * @author 周小桥 |2014-8-18 上午10:35:45
	 * @version 0.1
	 */
	private void setPageParm(HttpServletRequest request)
	{

		currPage = request.getParameter("page") != null ? Integer
				.parseInt(request.getParameter("page")) : 1;
		pageSize = request.getParameter("rows") != null ? Integer
				.parseInt(request.getParameter("rows")) : 1;
		sortname = request.getParameter("sidx");
		sortorder = request.getParameter("sord");
		request.setAttribute("page", currPage);
		request.setAttribute("rows", pageSize);
		request.setAttribute("sidx", sortname);
		request.setAttribute("sord", sortorder);
	}

	public Logger getLogger()
	{
		return logger;
	}

	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}

	public String getEid()
	{
		return eid;
	}

	public void setEid(String eid)
	{
		this.eid = eid;
	}

	public String getChina_name()
	{
		return china_name;
	}

	public void setChina_name(String china_name)
	{
		this.china_name = china_name;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public String getWork_history()
	{
		return work_history;
	}

	public void setWork_history(String work_history)
	{
		this.work_history = work_history;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getInterest()
	{
		return interest;
	}

	public void setInterest(String interest)
	{
		this.interest = interest;
	}

	public String getGraduate_time()
	{
		return graduate_time;
	}

	public void setGraduate_time(String graduate_time)
	{
		this.graduate_time = graduate_time;
	}

	public String getProfessional()
	{
		return professional;
	}

	public void setProfessional(String professional)
	{
		this.professional = professional;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getBorn_date()
	{
		return born_date;
	}

	public void setBorn_date(String born_date)
	{
		this.born_date = born_date;
	}

	public String getAdver_way()
	{
		return adver_way;
	}

	public void setAdver_way(String adver_way)
	{
		this.adver_way = adver_way;
	}

	public String getInterview_person()
	{
		return interview_person;
	}

	public void setInterview_person(String interview_person)
	{
		this.interview_person = interview_person;
	}

	public String getInterview_date()
	{
		return interview_date;
	}

	public void setInterview_date(String interview_date)
	{
		this.interview_date = interview_date;
	}

	public String getHope_salary()
	{
		return hope_salary;
	}

	public void setHope_salary(String hope_salary)
	{
		this.hope_salary = hope_salary;
	}

	public String getFact_salary()
	{
		return fact_salary;
	}

	public void setFact_salary(String fact_salary)
	{
		this.fact_salary = fact_salary;
	}

	public String getEmploy_status()
	{
		return employ_status;
	}

	public void setEmploy_status(String employ_status)
	{
		this.employ_status = employ_status;
	}

	public String getQual_cert()
	{
		return qual_cert;
	}

	public void setQual_cert(String qual_cert)
	{
		this.qual_cert = qual_cert;
	}

	public String getCome_date()
	{
		return come_date;
	}

	public void setCome_date(String come_date)
	{
		this.come_date = come_date;
	}

	public String getInterview_evaluate()
	{
		return interview_evaluate;
	}

	public void setInterview_evaluate(String interview_evaluate)
	{
		this.interview_evaluate = interview_evaluate;
	}

}