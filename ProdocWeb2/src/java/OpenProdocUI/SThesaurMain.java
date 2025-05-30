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
 * author: Joaquin Hierro      2016
 * 
 */

package OpenProdocUI;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhierrot
 */
public class SThesaurMain extends SParent
{
private static final String Html="<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>OpenProdoc2 Web</title>\n" +
"        <script src=\"js/OpenProdoc2.3.js\" type=\"text/javascript\"></script>\n" +
"        <script src=\"js/dhtmlx.js\" type=\"text/javascript\"></script>\n" +
"        <link rel=\"shortcut icon\" href=\"img/OpenProdoc.ico\" type=\"image/x-icon\">" +       
"        <link rel=\"STYLESHEET\" type=\"text/css\" href=\"js/dhtmlx.css\">\n" +
"        <link rel=\"STYLESHEET\" type=\"text/css\" href=\"css/OpenProdoc.css\">\n" +
"        <style>\n" +
"        html, body {\n" +
"                width: 100%;\n" +
"                height: 100%;\n" +
"                margin: 0px;\n" +
"                padding: 0px;\n" +
"                overflow: hidden;\n" +
"           }\n" +
"	</style>\n" +
"        </head>\n" +
"    <body onload=\"doOnLoadThes();\" >\n" +
"    <div id=\"MainBody\"></div>\n" +
"    </body>\n" +
"</html>";

//-----------------------------------------------------------------------------------------------
/**
 *
 * @param Req
 * @param out
 * @throws Exception
 */
@Override
protected void ProcessPage(HttpServletRequest Req, PrintWriter out) throws Exception
{
HttpSession Sess=Req.getSession();
out.println(Html);
}
//-----------------------------------------------------------------------------------------------

/** 
 * Returns a short description of the servlet.
 * @return a String containing servlet description
 */
@Override
public String getServletInfo()
{
return "SThesaurMain Servlet";
}
//-----------------------------------------------------------------------------------------------
}
