package cn.liyl.core.vo;

import org.springframework.data.domain.Pageable;

public interface IPageable extends Pageable{
	public Integer getStartRowNum();
	public Integer getEndRowNum();
}
