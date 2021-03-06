package nosql.workshop.resources;

import com.google.inject.Inject;
import net.codestory.http.Context;
import net.codestory.http.annotations.Get;
import nosql.workshop.model.Installation;
import nosql.workshop.model.stats.InstallationsStats;
import nosql.workshop.services.InstallationService;

import java.io.IOException;
import java.util.List;

/**
 * Resource permettant de gérer l'accès à l'API pour les Installations.
 */
public class InstallationResource {

    private final InstallationService installationService;

    @Inject
    public InstallationResource(InstallationService installationService) {
        this.installationService = installationService;
    }


    @Get("/")
    @Get("")
    public List<Installation> list(Context context) {
        return installationService.getAll();
    }

    @Get("/:numero")
    public Installation get(String numero) {
        return installationService.getById(numero);
    }


    @Get("/random")
    public Installation random() {
        return installationService.random();
    }

    @Get("/search")
    public List<Installation> search(Context context) throws IOException {
        System.out.println(context.query().getBoolean(""));
        return installationService.search(context);

    }

    @Get("/geosearch")
    public List<Installation> geosearch(Context context) {
        return installationService.geoSearch(context.query().getDouble("lat"),context.query().getDouble("lng"),context.query().getInteger("distance"));

    }

    @Get("/stats")
    public InstallationsStats stats() {
        return installationService.getStats();

    }
}
