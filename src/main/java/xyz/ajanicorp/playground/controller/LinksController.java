package xyz.ajanicorp.playground.controller;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import xyz.ajanicorp.playground.configuration.HttpHeadersProvider;
import xyz.ajanicorp.playground.constants.PlaygroundConstants;
import xyz.ajanicorp.playground.constants.PlaygroundStatusCodes;
import xyz.ajanicorp.playground.core.PlaygroundResult;
import xyz.ajanicorp.playground.domain.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xyz.ajanicorp.playground.service.LinkCrudService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajani on 17/10/2017.
 */
@RestController
public class LinksController {

    @Autowired
    LinkCrudService linkCrudService;

    @Autowired
    Gson gson;

    public static final Logger logger = LoggerFactory.getLogger(LinksController.class);

    @GetMapping(PlaygroundConstants.LINKS_BASE_URL)
    @ResponseBody
    public ResponseEntity<String> findAllLinks(){
        List<Link> links = new ArrayList<>();
        try{
            links = linkCrudService.findAll();
        }catch(Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<String>(PlaygroundStatusCodes.DEADBEEF,
                            PlaygroundConstants.DEADBEEF_TEXT))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(gson.toJson(
                new PlaygroundResult<List<Link>>(PlaygroundStatusCodes.SUP,links)
        ),HttpHeadersProvider.GetJsonHeaders(),HttpStatus.OK);
    }

    @PostMapping(PlaygroundConstants.LINKS_BASE_URL)
    @ResponseBody
    public ResponseEntity<String> addLink(@RequestBody String jsonRequest){
        try{
            Link link = gson.fromJson(jsonRequest,Link.class);

            if(linkCrudService.findByCode(link.getCode()) != null){
                return new ResponseEntity<String>(gson.toJson(
                        new PlaygroundResult<String>(PlaygroundStatusCodes.ALREADY_EXISTS,"Link already exists"))
                        ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.CONFLICT);
            }

            Link insertedLink = linkCrudService.save(link);

            return new ResponseEntity<String>(gson.toJson(
                    new PlaygroundResult<Link>(PlaygroundStatusCodes.SUP,insertedLink)),
                    HttpHeadersProvider.GetJsonHeaders(),HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new ResponseEntity<String>(gson
                    .toJson(new PlaygroundResult<String>(PlaygroundStatusCodes.DEADBEEF,
                            PlaygroundConstants.DEADBEEF_TEXT))
                    ,HttpHeadersProvider.GetJsonHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
