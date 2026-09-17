package com.filipe.relatorios.infrastructure.reports;

import com.filipe.relatorios.domain.model.Venda;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioPdfService
{
    public byte[] gerar( List<Venda> vendas )
    {
        try ( InputStream template = new ClassPathResource( "reports/vendas.jrxml" ).getInputStream() )
        {
            var jasperReport = JasperCompileManager.compileReport( template );
            JRDataSource dataSource = new JRBeanCollectionDataSource(
                    vendas.stream().map( venda -> new VendaReportRow(
                            venda.getCdVenda(),
                            venda.getCliente().getNmCliente(),
                            venda.getProduto().getNmProduto(),
                            venda.getQtQuantidade(),
                            venda.getVlTotal(),
                            venda.getDtVenda().toString()
                    ) ).toList()
            );

            JasperPrint print = JasperFillManager.fillReport( jasperReport, Map.of(), dataSource );
            return JasperExportManager.exportReportToPdf( print );
        }
        catch ( Exception exception )
        {
            throw new IllegalStateException( "Não foi possível gerar o relatório PDF.", exception );
        }
    }

    public record VendaReportRow(
            Long cdVenda,
            String cliente,
            String produto,
            Integer quantidade,
            java.math.BigDecimal total,
            String data
    ) { }
}
