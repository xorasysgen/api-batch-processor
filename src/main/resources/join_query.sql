SELECT  ldate,count(ldate)
  FROM nse_listedall group by ldate  order by count(ldate)  desc 

  select * from nse_listedall where company  ilike 'a%'
   select * from nse_listedall where company  ilike 't%' and faceval='10' order by ldate offset 72 limit 2

select * from indn500 
left outer join nse_listedall  on nse_listedall.isin =indn500.isin_code 
where nse_listedall.code is null or indn500.code is null
order by nse_listedall.code desc



select * from indn500 
right outer join nse_listedall  on nse_listedall.isin =indn500.isin_code 
where nse_listedall.code is null or indn500.code is null
order by nse_listedall.code desc



select * from nse_listedall 
select * from indn500 


right outer join nse_listedall  on nse_listedall.isin =indn500.isin_code 
where nse_listedall.code is null or indn500.code is null
order by nse_listedall.code desc


select * from indn500 
right outer join nse_listedall  on nse_listedall.isin =indn500.isin_code order by indn500.code

select * from indn500 
left join nse_listedall  on nse_listedall.isin =indn500.isin_code order by indn500.code

select * from indn500 
right join nse_listedall  on nse_listedall.isin =indn500.isin_code order by indn500.code
 