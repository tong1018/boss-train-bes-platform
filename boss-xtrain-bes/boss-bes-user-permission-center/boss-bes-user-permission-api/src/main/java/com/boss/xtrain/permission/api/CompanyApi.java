package com.boss.xtrain.permission.api;

import com.boss.xtrain.common.core.http.CommonPage;
import com.boss.xtrain.common.core.http.CommonPageRequest;
import com.boss.xtrain.common.core.http.CommonRequest;
import com.boss.xtrain.permission.pojo.dto.CompanyDTO;
import com.boss.xtrain.permission.pojo.query.CompanyQuery;
import com.boss.xtrain.permission.pojo.query.OrganizationQuery;
import com.boss.xtrain.permission.pojo.vo.CompanyVO;
import com.boss.xtrain.common.core.http.CommonResponse;
import com.boss.xtrain.common.core.web.controller.CommonCRUDApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 53534 秦昀清
 * @date 2020.07.07
 */
@RequestMapping("/education/bes/v1/company")
public interface CompanyApi extends CommonCRUDApi<CompanyDTO, CompanyQuery, CompanyVO> {

    /**
     * 查所有
     * @return 无条件查所有的公司列表
     * RequestBody @Valid CommonPageRequest<OrganizationQuery> commonRequest
     */
    @GetMapping("/selectAll")
    CommonResponse<List<CompanyVO>> selectAllCompany();

    /**
     * 查所有ORG以供选择
     * @return 所有的org
     * RequestBody @Valid CommonPageRequest<OrganizationQuery> commonRequest
     */
    @GetMapping("/selectAllOrgTree")
    CommonResponse<List<OrganizationQuery>> selectAllOrgToCombine();

    /**
     * 点击org获得company树
     * @param request 包含搜索条件的请求
     * @return org下的公司
     * RequestBody @Valid CommonPageRequest<OrganizationQuery> commonRequest
     */
    @PostMapping("/selectCompamyTreeByOrg")
    CommonResponse<List<CompanyQuery>> selectCombineCompany(@RequestBody @Valid CommonRequest<CompanyQuery> request);

    /**
     * 点击org获得company树
     * @return org下的公司
     * RequestBody @Valid CommonPageRequest<OrganizationQuery> commonRequest
     */
    @PostMapping("/selectByKey")
    CommonResponse<CompanyVO> selectByPrimaryKey(@RequestBody @Valid CommonRequest<CompanyQuery> request);

    /**
     * 分页条件搜索
     * @param request 包含分页及搜索条件的请求
     * @return 分页条件搜索下的公司列表
     */
    @PostMapping("/selectByPage")
    CommonResponse<CommonPage<CompanyVO>> selectByPage(@RequestBody @Valid CommonRequest<CommonPageRequest<CompanyQuery>> request);

    /**
     * 分页全搜索
     * @param request 包含分页及搜索条件的请求
     * @return 分页全搜索下的公司列表
     */
    @PostMapping("/selectAllByPage")
    CommonResponse<CommonPage<CompanyVO>> selectAllByPage(@RequestBody @Valid CommonRequest<CommonPageRequest> request);
}


