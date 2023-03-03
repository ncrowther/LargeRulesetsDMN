/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.File;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLGenerator {

    public static void main(String[] args) {
        try {
            // create a new XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().newDocument();

            // doc.getDocumentElement().setAttributeNS("http://www.omg.org/spec/DMN/20180521/MODEL/",
            // "xmlns:dmn", "Test");
            // doc.setPrefix("dmn");

            // create a root element
            Element root = doc.createElement("rules");
            doc.appendChild(root);

            for (int i = 0; i < 1000; i++) {
                // generate UUID and add to XML
                String ruleId = "_" + UUID.randomUUID().toString();
                Element ruleElement = doc.createElement("dmn:rule");
                ruleElement.setAttribute("id", ruleId);
                Node rule = root.appendChild(ruleElement);

                // generate input row 1 and add to XML
                String inputUuid = "_" + UUID.randomUUID().toString();
                Element inputEntry = doc.createElement("dmn:inputEntry");
                inputEntry.setAttribute("id", inputUuid);
                rule.appendChild(inputEntry);
                // generate data and add to XML
                Element textElement = doc.createElement("dmn:text");
                textElement.appendChild(doc.createTextNode("\"" + inputUuid + "\""));
                inputEntry.appendChild(textElement);

                // generate input row 2 and add to XML
                inputUuid = "_" + UUID.randomUUID().toString();
                inputEntry = doc.createElement("dmn:inputEntry");
                inputEntry.setAttribute("id", inputUuid);
                rule.appendChild(inputEntry);
                // generate data and add to XML
                textElement = doc.createElement("dmn:text");
                textElement.appendChild(doc.createTextNode("\"Green\""));
                inputEntry.appendChild(textElement);

                // generate input row 3 and add to XML
                inputUuid = "_" + UUID.randomUUID().toString();
                inputEntry = doc.createElement("dmn:inputEntry");
                inputEntry.setAttribute("id", inputUuid);
                rule.appendChild(inputEntry);
                // generate data and add to XML
                textElement = doc.createElement("dmn:text");
                textElement.appendChild(doc.createTextNode("\"Red\""));
                inputEntry.appendChild(textElement);

                // generate output row 1 and add to XML
                String outputUuid = "_" + UUID.randomUUID().toString();
                Element outputEntry = doc.createElement("dmn:outputEntry");
                outputEntry.setAttribute("id", outputUuid);
                rule.appendChild(outputEntry);
                // generate data and add to XML
                textElement = doc.createElement("dmn:text");
                textElement.appendChild(doc.createTextNode("\"Red\""));
                outputEntry.appendChild(textElement);

                // generate annotation entry
                Element annotationEntry = doc.createElement("dmn:annotationEntry");
                rule.appendChild(annotationEntry);
                // generate data and add to XML
                textElement = doc.createElement("dmn:text");
                textElement.appendChild(doc.createTextNode(""));
                annotationEntry.appendChild(textElement);
            }

            // create a new XML transformer
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // output the XML to the console or file
            DOMSource source = new DOMSource(doc);
            // StreamResult result = new StreamResult(System.out); // to print to console
            StreamResult result = new StreamResult(new File(
                    "D:\\A_Consultancy\\techsales\\2023\\BelfiusInsurance\\DmnSolution\\generated\\output.xml")); // to
                                                                                                                                                                  // write
                                                                                                                                                                  // to
                                                                                                                                                                  // file
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
