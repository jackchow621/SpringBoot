package ghost.springboot.controller;

import ghost.springboot.entity.Goods;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
	Map<Long, Goods> goodsMap = Collections
			.synchronizedMap(new HashMap<Long, Goods>());

	@ApiOperation(value = "获取商品列表", notes = "获取商品列表")
	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public List<Goods> getGoods() {
		List<Goods> goodsList = new ArrayList<Goods>(goodsMap.values());
		return goodsList;
	}

	@ApiOperation(value = "创建商品", notes = "创建商品")
	@ApiImplicitParam(name = "goods", value = "商品详细实体", required = true, dataType = "Goods")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postGoods(@RequestBody Goods goods) {
		goodsMap.put(goods.getId(), goods);
		return "success";
	}

	@ApiOperation(value = "获商品细信息", notes = "根据url的id来获取详细信息")
	@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Goods getGoods(@PathVariable Long id) {
		return goodsMap.get(id);
	}

	@ApiOperation(value = "更新信息", notes = "根据url的id来指定更新商品信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "goods", value = "商品实体goods", required = true, dataType = "Goods") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putGoods(@PathVariable Long id, @RequestBody Goods goods) {

		Goods goods1 = goodsMap.get(id);
		goods1.setName(goods.getName());
		goods1.setPrice(goods.getPrice());
		goodsMap.put(id, goods1);
		return "success";
	}

	@ApiOperation(value = "删除商品", notes = "根据url的id来指定删除商品")
	@ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteGoods(@PathVariable Long id) {
		goodsMap.remove(id);
		return "success";
	}

	@ApiIgnore// 使用该注解忽略这个API
	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	public String jsonTest() {
		return " hi jack!";
	}
}
