#!/usr/bin/perl

use strict;
use warnings;
use DBI;

my $db_name = 'cstore';
my $db_user = 'cadmin';
my $db_pass = 'cstore_GRP28_CSE21';
my $db_host = 'localhost';
my $db_port = '5432';

my $product_id = $ARGV[0];  # Get the product_id from command-line arguments

my $dbh = DBI->connect("dbi:Pg:dbname=$db_name;host=$db_host;port=$db_port", $db_user, $db_pass, { AutoCommit => 1 })
    or die "Could not connect to the database: $DBI::errstr";

my $sql = "SELECT SUM(count)
           FROM varies_on NATURAL LEFT OUTER JOIN inventory
           WHERE varies_on.product_id = ?";
my $sth = $dbh->prepare($sql);
$sth->execute($product_id);

my ($stock_count) = $sth->fetchrow_array();

print $stock_count;
