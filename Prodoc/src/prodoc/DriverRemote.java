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

package prodoc;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class implements a remote conection to a Prodoc Server
 * @author jhierrot
 */
public class DriverRemote  extends DriverGeneric
{
//URL OUrl=null;
//URLConnection OUrlc=null;
//HttpURLConnection URLCon=null;

static private PoolingHttpClientConnectionManager cm=null;
private CloseableHttpClient httpclient;
private HttpContext context;
private HttpPost UrlPost;

static final String charset="UTF-8";
OutputStreamWriter output;
//private static final String NEWLINE = "\r\n";

    /**
     *
     */
public static final String ORDER="Order";

    /**
     *
     */
    public static final String PARAM="Param";
boolean Conected=false;
StringBuilder Answer=new StringBuilder(3000);
//private List<String> cookies =null;
//private String SessionID =null;

final SimpleDateFormat formatterTS = new SimpleDateFormat("yyyyMMddHHmmss");
/**
 *
 */
final SimpleDateFormat formatterDate = new SimpleDateFormat("yyyyMMdd");

// DocumentBuilder DB=null;

/**
* 
* @param pURL
* @param pPARAM
* @param pUser
* @param pPassword
* @throws prodoc.PDException
*/
public DriverRemote(String pURL, String pPARAM, String pUser, String pPassword) throws PDException
{
super(pURL, pPARAM, pUser, pPassword);
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.DriverRemote>:"+pURL+"/"+pUser+"/"+pPARAM);
try {
if (pURL==null || pURL.length()<4)
    PDException.GenPDException("DATA_URL empty or wrong.",pURL);
httpclient=GetHttpClient();
UrlPost = new HttpPost(pURL);
context = new BasicHttpContext();
// DB =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
} catch (Exception ex)
    {
    PDException.GenPDException("Error_connecting_trough_URL"+pURL,ex.getLocalizedMessage());
    }
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.DriverRemote<");
}
//--------------------------------------------------------------------------
/**
 * Logged user acording his authenticator
 * @param userName
 * @param Password
 * @throws PDException
 */
@Override
void Assign(String userName, String Password) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.Assign>:"+userName);
ReadWrite(S_LOGIN,"<OPD><U>"+userName+"</U><C>"+Password+"</C></OPD>");
Conected=true;
getUser().LoadAll(userName);
getPDCust().Load(getUser().getCustom());
setAppLang(getPDCust().getLanguage());
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.Assign<:"+userName);
}

//--------------------------------------------------------------------------
/**
 * Disconects freeing all resources
 * @throws PDException In any error
 */
public void delete() throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.delete>:"+getURL());
try {
httpclient.close();
httpclient=null;
Conected=false;
} catch (Exception ex)
    {
    PDException.GenPDException("Error_closing_remote_connection",ex.getLocalizedMessage());
    }
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.delete<:"+getURL());
}
//--------------------------------------------------------------------------
/**
 * Verify if the conection with the repository is ok
 * @return true if the connection is valid
 */
public boolean isConnected()
{
return (Conected);
}
//--------------------------------------------------------------------------
/**
 * Create a table
 * @param TableName
 * @param Fields
 * @throws PDException 
 */
@Override
protected void CreateTable(String TableName, Record Fields) throws PDException
{
if (PDLog.isInfo())
    PDLog.Info("DriverRemote.CreateTable>:"+TableName+"/"+Fields);
ReadWrite(S_CREATE, "<OPD><Tab>"+TableName+"</Tab>"+Fields.toXMLt()+"</OPD>");
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.CreateTable<:"+TableName);
}
//--------------------------------------------------------------------------
/**
 * Drops a table
 * @param TableName
 * @throws PDException 
 */
@Override
protected void DropTable(String TableName) throws PDException
{
if (PDLog.isInfo())
    PDLog.Info("DriverRemote.DropTable>:"+TableName);
ReadWrite(S_DROP, "<OPD><Tab>"+TableName+"</Tab></OPD>");
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.DropTable<:"+TableName);
}
//--------------------------------------------------------------------------
/**
 * Modifies a table adding a field
 * @param TableName
 * @param NewAttr New field to add
     * @param IsVer
 * @throws PDException
 */
@Override
protected void AlterTableAdd(String TableName, Attribute NewAttr, boolean IsVer) throws PDException
{
if (PDLog.isInfo())
    PDLog.Info("DriverRemote.AlterTable>:"+TableName);
ReadWrite(S_ALTER, "<OPD><Tab>"+TableName+"</Tab>"+NewAttr.toXMLFull()+"<IsVer>"+(IsVer?"1":"0")+"</IsVer></OPD>");
if (PDLog.isInfo())
    PDLog.Info("DriverRemote.AlterTable<:"+TableName);
}
//--------------------------------------------------------------------------
/**
 * Modifies a table deleting a field
 * @param TableName
 * @param OldAttr old field to delete
 * @throws PDException
 */
@Override
protected void AlterTableDel(String TableName, String OldAttr) throws PDException
{
if (PDLog.isInfo())
    PDLog.Info("DriverRemote.AlterTable>:"+TableName);
ReadWrite(S_ALTERDEL, "<OPD><Tab>"+TableName+"</Tab><OldAttr>"+OldAttr+"</OldAttr></OPD>");
if (PDLog.isInfo())
    PDLog.Info("DriverRemote.AlterTable<:"+TableName);
}
//--------------------------------------------------------------------------
/**
 * Inserts a record/row
 * @param TableName
 * @param Fields
 * @throws PDException 
 */
@Override
protected void InsertRecord(String TableName, Record Fields) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.InsertRecord>:"+TableName+"="+Fields);
ReadWrite(S_INSERT, "<OPD><Tab>"+TableName+"</Tab>"+Fields.toXMLtNotNull()+"</OPD>");
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.InsertRecord<");
}
//--------------------------------------------------------------------------
/**
 * Deletes SEVERAL records acording conditions
 * @param TableName
 * @param DelConds
 * @throws PDException 
 */
@Override
protected void DeleteRecord(String TableName, Conditions DelConds) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.DeleteRecord>:"+TableName);
ReadWrite(S_DELETE, "<OPD><Tab>"+TableName+"</Tab><DelConds>"+DelConds.toXML()+"</DelConds></OPD>");
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.DeleteRecord<");   
}
//--------------------------------------------------------------------------
/**
 * Update SEVERAL records acording conditions
 * @param TableName
 * @param NewFields
 * @param UpConds
 * @throws PDException 
 */
@Override
protected void UpdateRecord(String TableName, Record NewFields, Conditions UpConds) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.UpdateRecord>:"+TableName+"="+NewFields);
ReadWrite(S_UPDATE, "<OPD><Tab>"+TableName+"</Tab>"+NewFields.toXMLt()+"<UpConds>"+UpConds.toXML()+"</UpConds></OPD>");
//ReadWrite(S_UPDATE, "<OPD><Tab>"+TableName+"</Tab>"+NewFields.toXMLtNotNull()+"<UpConds>"+UpConds.toXML()+"</UpConds></OPD>");
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.UpdateRecord<:"+TableName+"="+NewFields);
}
//--------------------------------------------------------------------------
/**
 * Add referential integrity between two tables and one field each
 * @param TableName1
 * @param Field1
 * @param TableName2
 * @param Field2
 * @throws PDException
 */
@Override
protected void AddIntegrity(String TableName1, String Field1, String TableName2, String Field2) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.AddIntegrity>:"+TableName1+","+Field1+"/"+TableName2+","+Field2);
ReadWrite(S_INTEGRIT, "<OPD><Tab1>"+TableName1+"</Tab1><Field1>"+Field1+"</Field1><Tab2>"+TableName2+"</Tab2><Field2>"+Field2+"</Field2></OPD>");
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.AddIntegrity<");
}
//--------------------------------------------------------------------------
/**
 * Add referential integrity between two tables and 2 fields each
 * @param TableName1
 * @param Field11 
 * @param Field12 
 * @param TableName2
 * @param Field21 
 * @param Field22 
 * @throws PDException
 */
@Override
protected void AddIntegrity(String TableName1, String Field11, String Field12, String TableName2, String Field21, String Field22) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.AddIntegrity2>:"+TableName1+","+Field11+","+Field12+"/"+TableName2+","+Field12+","+Field22);
ReadWrite(S_INTEGRIT2, "<OPD><Tab1>"+TableName1+"</Tab1><Field11>"+Field11+"</Field11><Field12>"+Field12+"</Field12><Tab2>"+TableName2+"</Tab2><Field21>"+Field21+"</Field21><Field22>"+Field22+"</Field22></OPD>");
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.AddIntegrity2<");
}
//-----------------------------------------------------------------------------------
/**
 * Starts a Transaction
 * @throws PDException In any error
 */
@Override
public void IniciarTrans() throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.InitTrans");
try {
ReadWrite(S_INITTRANS, "<OPD></OPD>");
} catch (Exception ex)
    {
    PDException.GenPDException("Error_starting_transaction",ex.getLocalizedMessage());
    }
setInTransaction(true);
}
//-----------------------------------------------------------------------------------
/**
 * Ends a transaction
 * @throws PDException In any error
 */
@Override
public void CerrarTrans() throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.CommitTrans");
try {
ReadWrite(S_COMMIT, "<OPD></OPD>");
setInTransaction(false);
} catch (Exception ex)
    {
    PDException.GenPDException("Error_closing_transaction",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------------------------
/**
 * Aborts a Transaction
 * @throws PDException In any error
 */
@Override
public void AnularTrans() throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.CancelTrans");
try {
ReadWrite(S_CANCEL, "<OPD></OPD>");
setInTransaction(false);
} catch (Exception ex)
    {
    PDException.GenPDException("Error_canceling_transaction",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------------------------
/**
 * Opens a cursor
 * @param Search
 * @return String identifier of the cursor
 * @throws PDException
 */
@Override
protected Cursor OpenCursor(Query Search) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.OpenCursor:"+Search);
Node N=ReadWrite(S_SELECT, "<OPD><Query>"+Search.toXML()+"</Query></OPD>");
Vector Res=new Vector();
NodeList RecLst = N.getChildNodes();
for (int i = 0; i < RecLst.getLength(); i++)
    {
    Node Rec = RecLst.item(i);
    Record R;
    R=Record.CreateFromXML(Rec);
    R.initList();
    for (int j = 0; j < R.NumAttr(); j++)
        {
        Attribute Attr=R.nextAttr();
        if (Attr.getName().contains("."+PDDocs.fVERSION))
            Attr.setName(PDDocs.fVERSION);
        else
        if (Attr.getName().contains("."+PDDocs.fPDID))
            Attr.setName(PDDocs.fPDID);
        }
    Res.add(R);
    }
Record RF=Search.getRetrieveFields();
RF.initList();
for (int j = 0; j < RF.NumAttr(); j++)
    {
    Attribute Attr=RF.nextAttr();
    if (Attr.getName().contains("."+PDDocs.fVERSION))
        Attr.setName(PDDocs.fVERSION);
    else
    if (Attr.getName().contains("."+PDDocs.fPDID))
        Attr.setName(PDDocs.fPDID);
    }
return(StoreCursor(Res, RF));
}

//-----------------------------------------------------------------------------------
/**
 * Close a Cursor
 * @param CursorIdent Identifier of the cursor to close
 * @throws PDException In any error
 */
@Override
public void CloseCursor(Cursor CursorIdent) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.CloseCursor:"+CursorIdent);
if (CursorIdent==null)
    return;
CursorIdent.CleanResultSet();
delCursor(CursorIdent);
}
//-----------------------------------------------------------------------------------
/**
 * Retrieves next record of cursor
 * @param CursorIdent Identifier of the cursor to travel
 * @return OPD next Record
 * @throws PDException In any error
 */
@Override
public Record NextRec(Cursor CursorIdent) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.NextRec:"+CursorIdent);
Vector rs=(Vector)CursorIdent.getResultSet();
if (rs.isEmpty())
    return(null);
Record Fields=CursorIdent.getFieldsCur();
Fields.assignSimil((Record)rs.get(0)); // description and other elemens from atribute not transmitted by performance
rs.remove(0);
return(Fields.Copy());
}
//-----------------------------------------------------------------------------------
/**
 * Communicates to the OpenProdoc Server by http sending instructionss
 * @param pOrder Order to execute
 * @param pParam Parameters of the order (can be empty or null depending on order
 * @return an xml node extracted form XML answer.
 * @throws PDException in any error
 */
private Node ReadWrite(String pOrder, String pParam) throws PDException
{
Node OPDObject=null;
DocumentBuilder DB=null;
CloseableHttpResponse response2 = null;
if (PDLog.isDebug())
    {
    PDLog.Debug("DriverRemote. ReadWrite: Order:"+pOrder);
    if (!pOrder.equals(S_LOGIN))
       PDLog.Debug("Param:"+pParam);
    else
       PDLog.Debug("Param:"+pParam.substring(0, 18));
    }
try {
DB =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
List <NameValuePair> nvps = new ArrayList <NameValuePair>();
nvps.add(new BasicNameValuePair(ORDER, pOrder));
nvps.add(new BasicNameValuePair(PARAM, pParam));
UrlPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
response2 = httpclient.execute(UrlPost, context);
HttpEntity Resp=response2.getEntity();
Document XMLObjects = DB.parse(Resp.getContent());
NodeList OPDObjectList = XMLObjects.getElementsByTagName("Result");
OPDObject = OPDObjectList.item(0);
if (OPDObject.getTextContent().equalsIgnoreCase("KO"))
    {
    OPDObjectList = XMLObjects.getElementsByTagName("Msg");
    if (OPDObjectList.getLength()>0)
        {
        OPDObject = OPDObjectList.item(0);
        PDException.GenPDException("Server_Error", DriverGeneric.DeCodif(OPDObject.getTextContent()));
        }
    else
        PDException.GenPDException("Server_Error", "");
    }
OPDObjectList = XMLObjects.getElementsByTagName("Data");
OPDObject = OPDObjectList.item(0);
} catch (Exception ex)
    {
    PDException.GenPDException(ex.getLocalizedMessage(), "");
    }
finally
    {
    if (response2!=null)    
        try {
            response2.close();
        } catch (IOException ex) 
            {
            PDException.GenPDException(ex.getLocalizedMessage(), "");
            }
    }
if (DB!=null)
    DB.reset();
return(OPDObject);
}
//-----------------------------------------------------------------   
/**
 * Allows to decide how to download file
 * @return true ir Driver is remote
 */
protected boolean IsRemote()
{
return(true);
}
//---------------------------------------------------------------------
/**
 *
 * @param RepName
 * @return
 * @throws PDException
 */
protected StoreGeneric getRepository(String RepName) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.getRepository>:"+RepName);
PDRepository RepDesc=new PDRepository(this);
RepDesc.Load(RepName);
StoreGeneric Rep=null;
try {
DocumentBuilder DB= DocumentBuilderFactory.newInstance().newDocumentBuilder();
Rep=new StoreRem(RepDesc.getURL(), RepDesc.getUser(), RepDesc.getPassword(), RepDesc.getParam(), RepDesc.isEncrypted(), UrlPost, httpclient, context, DB );
} catch (Exception Ex)
    {
    PDException.GenPDException(Ex.getLocalizedMessage(), "");
    }
return(Rep);
}
//-----------------------------------------------------------------------------------
/**
 * 
 * @throws PDException 
 */
void Logout() throws PDException
{
if (PDLog.isDebug()) 
    PDLog.Debug("DriverRemote.Logout");
try {
ReadWrite(S_LOGOUT, "<OPD></OPD>");
} catch (Exception ex)
    {
    PDException.GenPDException("Error_in_Logout",ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------------------------
/**
 *
 */
@Override
public void UnLock()
{
if (PDLog.isDebug()) 
    PDLog.Debug("DriverRemote.UnLock");
try {
ReadWrite(S_UNLOCK, "<OPD></OPD>");
super.UnLock();
} catch (Exception ex)
    {
    PDLog.Error(ex.getLocalizedMessage());
    }
}
//-----------------------------------------------------------------   

private CloseableHttpClient GetHttpClient()
{
//CloseableHttpClient httpclient = HttpClients.custom()
//                .setConnectionManager(GenPool())
//                .build();    
CloseableHttpClient httpclient = HttpClients.createDefault();
return(httpclient);
}
//-----------------------------------------------------------------------------------
static synchronized private PoolingHttpClientConnectionManager GenPool()
{
if (cm==null)
    {
    cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(100);
    ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();
    cm.setDefaultConnectionConfig(connectionConfig);
    }
return(cm);
}   
//-----------------------------------------------------------------------------------
/**
 * Returns an object of type Fulltext indexer
 * if the repository is yet constructed, returns the constructed one
     * @param pDocType
 * @return object of type repository
 * @throws PDException in any error
 */
@Override
protected FTConnector getFTRepository(String pDocType) throws PDException
{
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.getFTRepository>");
if (FTConn!=null)
    {
    if (PDLog.isDebug())
        PDLog.Debug("DriverRemote.Rep yet Instantiated");
    return (FTConn);
    }
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.Rep new Instance");
PDRepository RepDesc=new PDRepository(this);
RepDesc.Load("PD_FTRep");
try {
DocumentBuilder DB= DocumentBuilderFactory.newInstance().newDocumentBuilder();
FTConn=new FTRemote(RepDesc.getURL(), RepDesc.getUser(), RepDesc.getPassword(), RepDesc.getParam(), UrlPost, httpclient, context, DB );
} catch (Exception Ex)
    {
    PDException.GenPDException(Ex.getLocalizedMessage(), "");
    }
if (PDLog.isDebug())
    PDLog.Debug("DriverRemote.getFTRepository<");
return(FTConn);
}
//-----------------------------------------------------------------------------------

}
