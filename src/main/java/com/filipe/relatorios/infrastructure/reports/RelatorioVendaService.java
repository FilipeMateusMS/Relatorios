package com.filipe.relatorios.infrastructure.reports;

import com.filipe.relatorios.domain.model.Venda;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelatorioVendaService
{
    public byte[] gerarExcel( List<Venda> vendas )
    {
        try ( Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream output = new ByteArrayOutputStream() )
        {
            Sheet sheet = workbook.createSheet( "Vendas" );
            Row header = sheet.createRow( 0 );
            header.createCell( 0 ).setCellValue( "Código" );
            header.createCell( 1 ).setCellValue( "Cliente" );
            header.createCell( 2 ).setCellValue( "Produto" );
            header.createCell( 3 ).setCellValue( "Quantidade" );
            header.createCell( 4 ).setCellValue( "Total" );
            header.createCell( 5 ).setCellValue( "Data" );

            int rowIndex = 1;
            for ( Venda venda : vendas )
            {
                Row row = sheet.createRow( rowIndex++ );
                row.createCell( 0 ).setCellValue( venda.getCdVenda() );
                row.createCell( 1 ).setCellValue( venda.getCliente().getNmCliente() );
                row.createCell( 2 ).setCellValue( venda.getProduto().getNmProduto() );
                row.createCell( 3 ).setCellValue( venda.getQtQuantidade() );
                row.createCell( 4 ).setCellValue( venda.getVlTotal().doubleValue() );
                row.createCell( 5 ).setCellValue( venda.getDtVenda().toString() );
            }

            for ( int i = 0; i < 6; i++ )
            {
                sheet.autoSizeColumn( i );
            }

            workbook.write( output );
            return output.toByteArray();
        }
        catch ( IOException exception )
        {
            throw new IllegalStateException( "Não foi possível gerar o relatório Excel.", exception );
        }
    }

    public ResumoVenda resumir( List<Venda> vendas )
    {
        BigDecimal total = vendas.stream()
                .map( Venda::getVlTotal )
                .reduce( BigDecimal.ZERO, BigDecimal::add );

        return new ResumoVenda( vendas.size(), total );
    }

    public record ResumoVenda( int quantidadeVendas, BigDecimal faturamento ) { }
}
