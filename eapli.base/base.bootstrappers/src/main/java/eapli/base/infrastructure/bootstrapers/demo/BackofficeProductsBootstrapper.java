package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.ProductsBootstrapperBase;

import eapli.base.productmanagement.domain.model.Code;
import eapli.base.productmanagement.domain.model.Description;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.warehousemanagement.application.AislesRegistry;
import eapli.base.warehousemanagement.application.ListAislesController;
import eapli.base.warehousemanagement.application.RowRegistry;
import eapli.base.warehousemanagement.domain.model.*;
import eapli.framework.actions.Action;

import java.io.File;
import java.util.List;

public class BackofficeProductsBootstrapper extends ProductsBootstrapperBase implements Action {

    @Override
    public boolean execute(){
        ProductCategory calcado = registerProductCategory("11111", "Calçado");
        ProductCategory vinhos = registerProductCategory("22222", "Vinhos");
        ProductCategory livros = registerProductCategory("33333", "Livros");
        ProductCategory computadores = registerProductCategory("44444", "Computadores");
        ProductCategory telemoveis = registerProductCategory("55555", "Telemóveis");
        ProductCategory tablets = registerProductCategory("66666", "Tablets");
        ProductCategory espumantes = registerProductCategory("77777", "Espumantes");
        ProductCategory eletrodomesticos = registerProductCategory("88888", "Eletrodomésticos");
        ProductCategory televisoes = registerProductCategory("99999", "Televisores");
        ProductCategory jogos = registerProductCategory("10000", "Jogos");
        ProductCategory cameras = registerProductCategory("10101", "Câmaras");
        ProductCategory musica = registerProductCategory("20202", "Música");
        ProductCategory relogios = registerProductCategory("30303", "Relógios");

        //aisleIds
        List<Aisles> aislesList = (List<Aisles>) AislesRegistry.aislesManagementService().allAisles();
        AisleId a_id1 = aislesList.get(0).identity();
        AisleId a_id2 = aislesList.get(2).identity();
        AisleId a_id3 = aislesList.get(3).identity();
        AisleId a_id4 = aislesList.get(0).identity();
        AisleId a_id5 = aislesList.get(1).identity();

        List<AisleRow> aisleRows = (List<AisleRow>) RowRegistry.rowManagementService().allRows();
        //RowsIds
        RowsId a1_r_id1 = aislesList.get(0).rows().get(1).identity();
        RowsId a2_r_id1 = aislesList.get(2).rows().get(0).identity();
        RowsId a2_r_id2 = aislesList.get(3).rows().get(1).identity();
        RowsId a3_r_id1 = aislesList.get(0).rows().get(2).identity();
        RowsId a4_r_id1 = aislesList.get(1).rows().get(1).identity();


        //calçado
        registerProduct("140", "Adidas", "3", "sapatilhas adidas", "sapatilhas adidas nmd homem pretas e brancas", "cores: preto e branco, tamanho: 44", "3760118700793", "10", "45", "5", "1", calcado, new File("files/adidasnmd.png"), a_id1, a1_r_id1, 1L);
        registerProduct("100", "Adidas", "4", "sapatilhas adidas", "sapatilhas adidas stan smith homem brancas e verdes", "cores: verde e branco, tamanho: 44", "3760118700793", "10", "45", "5", "1", calcado, new File("files/adidasstansmith.png"), a_id1, a1_r_id1, 1L);
        registerProduct("110", "Nike", "5", "sapatilhas nike", "sapatilhas nike go fly ease homem pretas e brancas", "cores: preto e branco, tamanho: 44", "3760118700793", "14", "50", "7", "1", calcado, new File("files/nikegoflyease.png"), a_id1, a1_r_id1, 1L);

        //vinhos
        registerProduct("20", "Quinta do Vallado", "6", "vallado branco", "vinho valado reserva branco 2020", "vinho branco 13% alcool", "3760118700793", "1", "5", "17", "0.800", vinhos, new File("files/vinhobrancovallado.jpg"), a_id3, a3_r_id1 ,1L);

        //telemoveis
        registerProduct("1300", "Apple", "1", "iphone 13", "iphone 13 verde 128 gb", "128 gb, 4gb RAM, Apple A15 Bionic", "barcode", "8", "71", "146", "174", telemoveis, new File("files/iphone13.png"), a_id2, a2_r_id1, 1L);
        registerProduct("1600", "Apple", "2", "iphone 13", "iphone 13 pro max verde 128 gb", "128 gb, 6gb RAM, Apple A15 Bionic", "3760118700793", "8", "71", "160", "0.200", telemoveis, new File("files/iphone13.png"), a_id2, a2_r_id1, 1L);
        registerProduct("480", "Samsung", "16", "samsung A53", "telemóvel samsung A53 5G", "Ecrã 6.5'' Super AMOLED FHD+, Câmara 64MP OIS", "3760118700793", "7", "79", "120", "0.200", telemoveis, new File("files/samsungA53.png"), a_id2, a2_r_id1, 1L);
        registerProduct("418", "Samsung", "17", "samsung zflip", "telemóvel samsung galaxy z flip 5G", "256 GB, design dobrável e vidro ultrafino Samsung", "barcode17", "7", "79", "130", "0.210", telemoveis, new File("files/samsungzflip.png"), a_id2, a2_r_id1, 1L);

        //relogios
        registerProduct("8650", "Rolex", "7", "rolex submariner", "relogio profissional rolex submariner oyster", "submariner oyster, 41 ", "3760118700793", "20", "19", "40", "0.119", relogios, new File("files/rolexsubmariner.png"), a_id4, a4_r_id1, 1L);
        registerProduct("565", "Tissot", "8", "tissot seastar", "relogio tissot seastar 1000 chronograph", "seastar 1000 chronograph, vidro: safira, caixa : aço", "3760118700793", "20", "18", "45", "0.119", relogios, new File("files/relogiotissot.png"), a_id4, a4_r_id1, 1L);

        //jogos
        registerProduct("69", "EA Sports", "9", "fifa22", "jogo fifa22 para a ps4 " ,"jogo ps4 fifa22", "3760118700793", "1", "5", "6", "0.07", jogos, new File("files/fifa22ps4.png"), a_id2, a2_r_id2, 2L);
        registerProduct("69", "EA Sports", "10", "nba2k22", "jogo nba2k22 para a ps4 " ,"jogo ps4 nba2k22", "3760118700793", "1", "5", "6", "0.07", jogos, new File("files/nba2k22ps4.jpg"), a_id2, a2_r_id2, 1L);
        registerProduct("69", "Activision", "11", "cod vanguard", "jogo call of dutty vanguard para a xbox " ,"jogo xbox call of dutty vanguard", "3760118700793", "1", "5", "6", "0.07", jogos, new File("files/codvanguardxbox.jpg"), a_id2, a2_r_id2, 1L);
        registerProduct("10", "Rockstar Games", "12", "gta 5", "jogo ps4 grand theft auto five" ,"jogo ps4 grand theft auto five", "3760118700793", "1", "5", "6", "0.07", jogos, new File("files/gta5ps4.png"), a_id2, a2_r_id2, 1L);
        registerProduct("20", "EA Sports", "13", "fifa21", "jogo fifa21 para a ps4 " ,"jogo ps4 fifa21", "3760118700793", "1", "5", "6", "0.07", jogos, new File("files/fifa21ps4.png"), a_id2, a2_r_id2, 1L);
        registerProduct("18", "EA Sports", "14", "nba2k21", "jogo nba2k21 para a ps4 " ,"jogo ps4 nba2k21", "3760118700793", "1", "5", "6", "0.07", jogos, new File("files/nba2k21ps4.jpg"), a_id2, a2_r_id2, 1L);
        registerProduct("10", "Activision", "15", "cod infinite warfare", "jogo call of dutty infinite warfare para a xbox one" ,"jogo xbox one call of dutty infinite warfare", "3760118700793", "1", "5", "6", "0.07", jogos, new File("files/codinfinitexbox.jpg"), a_id2, a2_r_id2, 1L);

        return true;
    }


}
