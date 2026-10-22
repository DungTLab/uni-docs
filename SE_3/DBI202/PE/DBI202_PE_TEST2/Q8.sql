CREATE PROCEDURE proc_SumQuantityProduct
	@store_id INT,
	@SumQuantityProduct INT OUTPUT

AS
BEGIN
	SELECT @SumQuantityProduct = SUM(quantity)
	FROM stocks
	WHERE store_id = @store_id
END;