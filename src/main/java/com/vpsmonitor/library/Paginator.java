package com.vpsmonitor.library;

import java.util.ArrayList;
import java.util.List;

public class Paginator <T>
{
	private List<T> icollection;
	private int pg_num;
	private int items_per_page;
	
	public Paginator(List<T> ncoll)
	{
		this.icollection = ncoll;
	}
	
	public void setCurrentPageNumber(int page) { if(page <= 0) { page = 1; } this.pg_num = page; }
	public void setItemCountPerPage(int count) { this.items_per_page = count; }
	
	
	public int getCurrentPageNumber() { return this.pg_num; }
	public int getItemCountPerPage() { return this.items_per_page; }
	public int getCount() { return this.icollection.size(); }
	public List<T> getCurrentItems()
	{ 
		List<T> tmp = new ArrayList<T>();
		int start, end, count;

		start = (this.pg_num - 1) * this.items_per_page;
		end = (this.pg_num) * this.items_per_page;
		if(end > this.icollection.size() )
			end = this.icollection.size();
		
		count = 0;
		for(Object i : this.icollection)
		{
			if( count >= start && count < end )
				tmp.add((T)i);
			count++;
		}
		return tmp;
	}

}
