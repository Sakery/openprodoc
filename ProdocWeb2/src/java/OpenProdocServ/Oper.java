/*
 * OpenProdoc
 * 
 * See the help doc files distributed with
 * this work for additional information regarding copyright ownership.
 * Joaquin Hierro licenses this file to You under:
 * 
 * License GNU Affero GPL v3 http://www.gnu.org/licenses/agpl.html
 * 
 * you may not use this file except in compliance with the License.  
 * Unless agreed to in writing, software is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * author: Joaquin Hierro      2011
 * 
 */

/*
 * Oper.java
 *
 * Created on 29-ene-2014, 0:52:22
 */


package OpenProdocServ;

import OpenProdocUI.SParent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import prodoc.DriverGeneric;
import prodoc.DriverRemote;
import prodoc.PDDocs;
import prodoc.PDException;
import prodoc.PDLog;
import prodoc.PDMimeType;
import prodoc.ProdocFW;


/**
 *
 * @author jhierrot
 */
public class Oper extends HttpServlet
{

    /**
     *
     */
    protected static String ProdocProperRef=null;

private static boolean FWStartted=false;

/** Initializes the servlet.
 * @param config 
 * @throws ServletException 
 */
public void init(ServletConfig config) throws ServletException
{
super.init(config);
}
//-----------------------------------------------------------------------------------------------

/** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException
 * @throws IOException
*/
protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
response.setContentType("text/xml;charset=UTF-8");
response.setStatus(HttpServletResponse.SC_OK);
try {
if (!FWStartted)    
    {
    StartFW();
    FWStartted=true;
    }
if (PDLog.isDebug())
   PDLog.Debug("##########################################################################");  
if (Connected(request) && request.getContentType().contains("multipart"))
    {
    InsFile(request, response);
    return;    
    }
if (request.getParameter(DriverRemote.ORDER)==null)
    {
    PrintWriter out = response.getWriter();      
    Answer(request, out, "<OPD><Result>KO</Result><Msg>Disconnected</Msg></OPD>");
    out.close();
    return;
    }
String Order=request.getParameter(DriverRemote.ORDER);   
if (Connected(request) && Order.equals(DriverGeneric.S_RETRIEVEFILE)) 
    {
    SendFile(request, response);
    return;
    }
if (!Connected(request) || Order.equals(DriverGeneric.S_LOGOUT)) 
    {
    SParent.ClearSessOPD(request.getSession());
    }
if (Connected(request) || Order.equals(DriverGeneric.S_LOGIN)) 
    {
    PrintWriter out = response.getWriter();      
    ProcessPage(request, out);
    out.close();
    }
else
    {
    PrintWriter out = response.getWriter();      
    Answer(request, out, false, "<OPD><Result>KO</Result><Msg>Disconnected</Msg></OPD>", null);
    out.close();
    }
} catch (Exception e)
    {
    PrintWriter out = response.getWriter();      
    AddLog(e.getMessage());
    Answer(request, out, false, e.getMessage(), null);
    out.close();
    }
}
//-----------------------------------------------------------------------------------------------
/**
 *
 * @param Req
 * @param out
 * @param Ok Result of operation
 * @param Message Optional Message
 * @param Data Optional XML Data
 */
static public void Answer(HttpServletRequest Req, PrintWriter out, boolean Ok, String Message, String Data)
{
out.println("<OPD>");
out.println("<Result>"+(Ok?"OK":"KO")+"</Result>");
if (Message!=null && Message.length()!=0)
    out.println("<Msg>"+Message.replace('<', '^')+"</Msg>");
if (Data!=null && Data.length()!=0)
    out.println("<Data>"+Data+"</Data>");    
if (PDLog.isDebug())
    {
    PDLog.Debug("Answer:<Result>"+(Ok?"OK":"KO")+"</Result>");
    if (Message!=null && Message.length()!=0)
        PDLog.Debug("<Msg>"+Message+"</Msg>");
    if (Data!=null && Data.length()!=0)
        PDLog.Debug("<Data>"+Data+"</Data>");    
    }
out.println("</OPD>");
}
//-----------------------------------------------------------------------------------------------
/**
 *
 * @param Req
 * @param out
 * @param AllMessage
 */
static public void Answer(HttpServletRequest Req, PrintWriter out, String AllMessage)
{
if (PDLog.isDebug())
    PDLog.Debug("Answer:"+AllMessage);
out.println(AllMessage);
}
//-----------------------------------------------------------------------------------------------

/**
 * 
 * @param Texto
 */
protected void AddLog(String Texto)
{
System.out.println(">> "+this.getServletName()+":"+new Date()+"="+Texto);
}
//-----------------------------------------------------------------------------------------------
/**
 * 
 * @param Req
 * @return
 * @throws PDException
 */
protected boolean Connected(HttpServletRequest Req) throws Exception
{
if (SParent.getSessOPD(Req)==null)
    return(false);
else
    return(true);
}
//-----------------------------------------------------------------------------------------------
/**
 * 
 * @param Req
 * @param out
 * @throws Exception
 */
protected void ProcessPage(HttpServletRequest Req, PrintWriter out) throws Exception
{
String Order=Req.getParameter(DriverRemote.ORDER);   
String Param=Req.getParameter(DriverRemote.PARAM);   
if (PDLog.isDebug())
    {
    PDLog.Debug("From:"+Req.getRemoteHost()+"/"+Req.getRemoteHost()+":"+Req.getRemoteUser());
    PDLog.Debug("Order:"+Order);
    PDLog.Debug("Param:"+Param);
    }
DocumentBuilder DB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
Document XMLObjects = DB.parse(new ByteArrayInputStream(Param.getBytes("UTF-8")));
if (Order.equals(DriverGeneric.S_LOGIN)) 
    {
    NodeList OPDObjectList = XMLObjects.getElementsByTagName("U");
    Node OPDObject = OPDObjectList.item(0);
    String User=OPDObject.getTextContent(); 
    OPDObjectList = XMLObjects.getElementsByTagName("C");
    OPDObject = OPDObjectList.item(0);
    String Pass=OPDObject.getTextContent(); 
    DriverGeneric D=ProdocFW.getSession("PD", User, Pass);
    SParent.setSessOPD(Req, D);
    Answer(Req, out, true, null, null);
    return;    
    }
else if (Order.equals(DriverGeneric.S_UNLOCK)) 
    {
//    getSessOPD(Req).UnLock();
//    Req.getSession().setAttribute("PRODOC_SESS", null);   
    SParent.ClearSessOPD(Req.getSession());
    Answer(Req, out, true, null, null);
    return;    
    }
DriverGeneric D=SParent.getSessOPD(Req);
String Results=D.RemoteOrder(Order, XMLObjects);
Answer(Req, out, Results);
XMLObjects=null;
DB.reset();
}
//-----------------------------------------------------------------------------------------------

/** Handles the HTTP <code>GET</code> method.
* @param request servlet request
 * @param response servlet response
 * @throws ServletException
 * @throws IOException  
*/
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
processRequest(request, response);
}
//-----------------------------------------------------------------------------------------------

/** Handles the HTTP <code>POST</code> method.
* @param request servlet request
 * @param response servlet response
 * @throws ServletException
 * @throws IOException
*/
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
processRequest(request, response);
}
//-----------------------------------------------------------------------------------------------

/** Returns a short description of the servlet.
 * @return 
 */
@Override
public String getServletInfo()
{
return "Servlet for Oper";
}
//-----------------------------------------------------------------------------------------------
///**
// *
// * @param Req
// * @return
// */
//public static DriverGeneric getSessOPD(HttpServletRequest Req)
//{
//return (DriverGeneric)Req.getSession(true).getAttribute("PRODOC_SESS");
//}
//--------------------------------------------------------------
///**
// *
// * @param Req
// * @param OPDSess
// */
//public static void setSessOPD(HttpServletRequest Req, DriverGeneric OPDSess)
//{
//Req.getSession().setAttribute("PRODOC_SESS", OPDSess);
//}
//--------------------------------------------------------------
/**
 * 
 * @return
 */
static public String getVersion()
{
return("0.9");
}
//-----------------------------------------------------------------------------------------------
private void StartFW() throws Exception
{
ProdocFW.InitProdoc("PD", SParent.getProdocProperRef());    
}
//----------------------------------------------------------   

private void SendFile(HttpServletRequest Req, HttpServletResponse response) throws Exception
{
String Param=Req.getParameter(DriverRemote.PARAM);   
if (PDLog.isDebug())
    PDLog.Debug("SendFile Param:"+Param);
DocumentBuilder DB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
Document XMLObjects = DB.parse(new ByteArrayInputStream(Param.getBytes("UTF-8")));
NodeList OPDObjectList = XMLObjects.getElementsByTagName("Id");
Node OPDObject = OPDObjectList.item(0);
String Id=OPDObject.getTextContent();
OPDObjectList = XMLObjects.getElementsByTagName("Ver");
OPDObject = OPDObjectList.item(0);
String Ver=OPDObject.getTextContent();
DB.reset();
PDDocs doc=new PDDocs(SParent.getSessOPD(Req));
doc.setPDId(Id);
if (Ver!=null && Ver.length()!=0)
    doc.LoadVersion(Id, Ver);
else
    doc.LoadCurrent(Id);
ServletOutputStream out=response.getOutputStream();
PDMimeType mt=new PDMimeType(SParent.getSessOPD(Req));
mt.Load(doc.getMimeType());
response.setContentType(mt.getMimeCode());
response.setHeader("Content-disposition", "inline; filename=" + doc.getName());
try {
if (Ver!=null && Ver.length()!=0)
    doc.getStreamVer(out);
else
    doc.getStream(out);
} catch (Exception e)
    {
    out.close();
    throw e;
    }
out.close();
}
//----------------------------------------------------------   
/**
 * 
 * @param request
 * @param response 
 */
private void InsFile(HttpServletRequest Req, HttpServletResponse response) throws Exception
{
if (PDLog.isDebug())
    PDLog.Debug("InsFile");
FileItem ItemFile=null;    
InputStream FileData=null;
HashMap ListFields=new HashMap();
DiskFileItemFactory factory = new DiskFileItemFactory();
factory.setSizeThreshold(1000000);
ServletFileUpload upload = new ServletFileUpload(factory);
List items = upload.parseRequest(Req);
Iterator iter = items.iterator();
while (iter.hasNext())
    {
    FileItem item = (FileItem) iter.next();
    if (item.isFormField())
        ListFields.put(item.getFieldName(), item.getString());
    else 
        {
        FileData=item.getInputStream();
        ItemFile=item;
        }
    }
DriverGeneric PDSession=SParent.getSessOPD(Req);
String Id=(String) ListFields.get("Id");
String Ver=(String) ListFields.get("Ver");
PDSession.InsertFile(Id, Ver, FileData);
if (FileData!=null)
    FileData.close();
if (ItemFile!=null)
    ItemFile.delete();
items.clear(); // to help and speed gc
PrintWriter out = response.getWriter(); 
Answer(Req, out, true, null, null);
out.close();
}
//----------------------------------------------------------   
}

