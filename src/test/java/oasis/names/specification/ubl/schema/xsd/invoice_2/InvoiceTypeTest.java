/*
 * Copyright 2016 JMarkStar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package oasis.names.specification.ubl.schema.xsd.invoice_2;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueDateType;
import org.junit.Test;

/**
 *
 * @author jmarkstar
 */
public class InvoiceTypeTest {
    
    @Test
    public void createFacturaEjemplo1() throws DatatypeConfigurationException, JAXBException, ParseException{
        System.out.println("El metodo createFacturaEjemplo1 ha iniciado");

        InvoiceType facturaElectronica = new InvoiceType();
        
        //Agregando la fecha de la operacion
        IssueDateType fechaDeOperacion = new IssueDateType();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaEspecificada = dateFormat.parse( "2012-03-14" );
        GregorianCalendar gcFechaOperacion = new GregorianCalendar();
        gcFechaOperacion.setTime(fechaEspecificada);
        fechaDeOperacion.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
            gcFechaOperacion.get(Calendar.YEAR), 
            gcFechaOperacion.get(Calendar.MONTH)+1, 
            gcFechaOperacion.get(Calendar.DAY_OF_MONTH),
            DatatypeConstants.FIELD_UNDEFINED));
        facturaElectronica.setIssueDate(fechaDeOperacion);
        
        //Marshal the InvoiceType Object
        JAXBContext jaxbContext = JAXBContext.newInstance(InvoiceType.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //replace file
        File xmlInvoiceFile = new File("generated_documents/Factura_electronica_ejemplo_1.xml");
        if(xmlInvoiceFile.exists())
            xmlInvoiceFile.delete();
        jaxbMarshaller.marshal(facturaElectronica, xmlInvoiceFile);
        jaxbMarshaller.marshal(facturaElectronica, System.out);
        System.out.println("El metodo createFacturaEjemplo1 ha terminado.");
    }
    
    @Test
    public void createFacturaEjemplo2(){
        System.out.println("createFacturaEjemplo2");
    }
}
