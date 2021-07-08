-- show first 10 rows
select * from retail where quantity < 0 limit 10; 

-- check # of records
select count(*) as num_records from retail; 

-- number of clients with unique ids
select count(*) as num_customers from (select distinct customer_id from retail) as unique_customers;

-- invoice date range
select max(invoice_date), min(invoice_date) from retail; 

-- number of sku/merchants with unique stock codes
select count(*)  as num_skus from (select distinct stock_code from retail) as unique_stock_codes;


-- Calculate average invoice amount excluding invoices with a negative amount
select avg(amount) from (select invoice_no, sum(quantity*unit_price) as amount from retail group by invoice_no having sum(quantity*unit_price) > 0) as invoices;


-- calculate total revenue
select sum(quantity*unit_price) from retail;

--  Calculate total revenue by YYYYMM
select (extract(year from invoice_date)*100 + extract(month from invoice_date)) as yyyymm, sum(quantity*unit_price) from retail group by (extract(year from invoice_date)*100 + extract(month from invoice_date)) order by yyyymm; 
