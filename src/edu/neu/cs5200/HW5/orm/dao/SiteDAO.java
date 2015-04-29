package edu.neu.cs5200.HW5.orm.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import sun.nio.cs.ext.ISCII91;
import edu.neu.cs5200.HW5.orm.models.Site;
import edu.neu.cs5200.HW5.orm.models.SiteList;


//@Path("/site")
public class SiteDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cs5200.HW5");
	//�˴������project�����֣�����MySQL��ģ�����
	EntityManager em = factory.createEntityManager();
	
	//CRUD=create,read,update,delete����ɾ��
	
	//findSiteById
//	@GET
//	@Path("/{ID}")
//	@Produces(MediaType.APPLICATION_JSON)
//	���json��ʽ
//	@PathParam("ID") 
	public Site findSite(Integer siteId) {
	return em.find(Site.class, siteId);
	//ɶ�����øɣ�find�ͺã���Ϊû�ж����ݿ���в�����Ҳ����getTransaction
	}
	
	//findAllSites
//	@GET
//	@Path("/")
//	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		//select all instance of Site from class Site, not table Site
		//������javax��query language������sql
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
		//query.getResultList()�õ�����list of objects,cast��List<Site>��return
	}
	
	
	public void exportSiteDatabaseToXmlFile(SiteList siteList, String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
		JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
		Marshaller marshaller = jaxb.createMarshaller();
//					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					marshaller.marshal(siteList, xmlFile);
				} catch (JAXBException e) {
		// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	public void convertXmlFileToOutputFile(
			String inputXmlFileName,
			String outputXmlFileName,
			String xsltFileName)
				{
			File inputXmlFile = new File(inputXmlFileName);
			File outputXmlFile = new File(outputXmlFileName);
			File xsltFile = new File(xsltFileName);
			StreamSource source = new StreamSource(inputXmlFile);
			StreamSource xslt    = new StreamSource(xsltFile);
			StreamResult output = new StreamResult(outputXmlFile);
			TransformerFactory factory = TransformerFactory.newInstance();
			try {
			Transformer transformer = factory.newTransformer(xslt);
						transformer.transform(source, output);
					} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TransformerException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
//	//createSite
//	//Ҳ������@GET�������˴���@POST@Consume,��˵����json�����ݽ���post����
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Site> createSite(Site site) {
//		List<Site> sites = new ArrayList<Site>();
//		
//		//��ʼgetTransaction,���commit������
//		em.getTransaction().begin();
//		em.persist(site);
//		//make it into database
//		//��ʱmovie��Ϊmanaged object��
//		//��ʱ�����еĸĶ����Ѿ���database�е�record�������� 
//		
//		Query query = em.createQuery("select site from Site site");
//		sites = (List<Site>)query.getResultList();
//		em.getTransaction().commit(); 
//		return sites;
//	}	
//		
//		
//	//updateSite
//	@PUT
//	@Path("/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Site> updateSite(@PathParam("id") int siteId, Site site) {
//		List<Site> sites = new ArrayList<Site>();
//		
//		em.getTransaction().begin();
//		site.setId(siteId);
//		//�����site��id����Ϊ���ݽ�����ID����Ϊmergeֻ����objectΪ����
//		//���site���´��������ڴ��ݲ�������ʱ����
//		em.merge(site);
//		//�ϲ������site���� correspond to ��database�е�site���в�ͬ�ģ���֮
//		
//		//�������query.getResultList�����������List
//		Query query = em.createQuery("select site from Site site");
//		sites = (List<Site>)query.getResultList();
//		em.getTransaction().commit();
//		return sites;
//	}		
//		
//	//removeSite
//	@DELETE
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Site> removeSite(@PathParam("id") int siteId) {
//		List<Site> sites = new ArrayList<Site>();
//		
//		em.getTransaction().begin();
//		Site site = em.find(Site.class, siteId);
////		em.createQuery("UPDATE Site site SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
//		//ɾ����ɺ����� vs �������йصĶ�ɾ��
//		//SET FOREIGN_KEY_CHECKS = 1; 
//		em.remove(site);
////		em.createQuery("UPDATE Site site SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
//		
//		Query query = em.createQuery("select site from Site site");
//		sites = (List<Site>)query.getResultList();
//		em.getTransaction().commit();
//		return sites;
//	}

	
	
	public static void main(String[] args) {
		
		SiteDAO dao = new SiteDAO();

		//findSiteById
		
//		Site site = dao.findSite(1);
//		System.out.println(site.getName());
		
		//findAllSites
		
		List<Site> sites = dao.findAllSites();
		for(Site site : sites) {
			System.out.println(site.getName());
		}
		
//		
		

		SiteList theSites = new SiteList();
		theSites.setSites(sites);
		dao.exportSiteDatabaseToXmlFile(theSites, "xmlsites.xml");
//		Because / is not allowed in a file's name, I just use xmlsites.xml instead of xml/sites.xml
		dao.convertXmlFileToOutputFile("xmlsites.xml", "xmlsites.html", "sites2html.xslt");
		dao.convertXmlFileToOutputFile("xmlsites.xml", "xmlequipment.html", "sites2equipment.xslt");

		
		
		//updateSite
		//����Ҫ���½�һ��sister site��Ȼ����set��Ҫ���ĵ�attribute��
		//�����Ż���ֻ����ĳ��attribute��������ʣ�µĶ���Ϊ0����Ϊû���ݣ�
		
//		Site newSite = dao.findSite(1);
//		newSite.setName("NewSite 1");
//		
//		List<Site> sites = dao.updateSite(1,newSite);
//		for(Site site : sites) {
//			System.out.println(site.getName());
//		}
		
		//removeSite
//		dao.removeSite(1);
//		List<Site> sites = dao.findAllSites();
//		for(Site site : sites) {
//			System.out.println(site.getName());
//		}
		
		//createSite
//		Site site = new Site(4, "Site 4", 11.11, 44.44);
//		
//		dao.createSite(site);
//		List<Site> sites = dao.findAllSites();
//		for(Site site1 : sites) {
//			System.out.println(site1.getName());
//		}

		
		
	}
}
